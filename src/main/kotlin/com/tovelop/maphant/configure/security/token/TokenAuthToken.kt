package com.tovelop.maphant.configure.security.token

import com.tovelop.maphant.configure.security.UserData
import com.tovelop.maphant.dto.UserDataDTO
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.GrantedAuthority

class TokenAuthToken(
    private val headerAuth: String,
    private val headerTS: Int,
    private val headerSign: String,
    private val userData: UserData? = null,
    authorities: MutableCollection<out GrantedAuthority>? = null,
): AbstractAuthenticationToken(authorities) {

    override fun getCredentials(): Triple<String, Int, String> = Triple(headerAuth, headerTS, headerSign)
    override fun getPrincipal() = headerAuth

    fun isExpired(): Boolean {
        return false
    }

    fun createToken(timestamp: Int, privToken: String): String {
        return privToken
    }

    override fun isAuthenticated() = userData != null

    fun getUserData(): UserDataDTO {
        return userData?.getUserData() ?: throw BadCredentialsException("No user")
    }
}