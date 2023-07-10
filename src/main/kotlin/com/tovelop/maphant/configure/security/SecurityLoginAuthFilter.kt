package com.tovelop.maphant.configure.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component

@Component
class SecurityLoginAuthFilter(authenticationManager: AuthenticationManager):
AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/user/login", "POST"), authenticationManager){

    init {
        this.authenticationManager = authenticationManager
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        val user = request?.getParameter("email") ?: throw BadCredentialsException("no email field")
        val password = request.getParameter("password") ?: throw BadCredentialsException("no password field")

        val authReq = SecurityLoginAuthToken(user, password)

        return this.authenticationManager.authenticate(authReq)
    }


}