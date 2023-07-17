package com.tovelop.maphant.configure.security.filter

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.utils.ResponseJsonWriter.Companion.writeJSON
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.stereotype.Component

@Component
class TokenAuthFilter(authenticationManager: AuthenticationManager?)
    : AbstractAuthenticationProcessingFilter("/**", authenticationManager) {

    init {
        this.authenticationManager = authenticationManager
        this.setAuthenticationSuccessHandler { request, response, authentication ->
            run {
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        this.setAuthenticationFailureHandler { request, response, exception ->
            run {
                val output = mutableMapOf<String, Any>(
                    "status" to false,
                    "message" to (exception.message ?: "unexpected error")
                )

                response.status = 401
                response.writeJSON(output)
            }
        }
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        // pubKey
        val headerAuth = request?.getHeader("x-auth") ?: throw BadCredentialsException("No auth header")

        // time stamp
        val headerTS = request.getHeader("x-timestamp").toInt() ?: throw BadCredentialsException("No timestamp header")

        // privKey * timestamp
        val headerSign = request.getHeader("x-sign") ?: throw BadCredentialsException("No sign header")


//        val epoch = System.currentTimeMillis() / 1000
//        if(epoch - headerTS > 60) {
//            throw SecurityException("Timestamp expired")
//        }

        val authReq = TokenAuthToken(headerAuth, headerTS, headerSign)

        return this.authenticationManager.authenticate(authReq)
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        if(httpRequest.getHeader("x-auth") != null) {
            super.doFilter(request, response, chain)
        } else {
            chain.doFilter(request, response)
        }
    }
}