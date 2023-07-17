package com.tovelop.maphant.configure.security.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoginAuthToken(
    private val username: String,
    private val password: String,
    private val userData: UserDetails? = null,
    authorities: MutableCollection<out GrantedAuthority>? = null,
): AbstractAuthenticationToken(authorities) {
    override fun getCredentials() = password

    override fun getPrincipal() = username

    override fun isAuthenticated() = userData != null
}