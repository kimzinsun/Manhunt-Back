package com.tovelop.maphant.configure.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority


class SecurityLoginAuthToken(private val email: String,
                             private val password: String,
                             authorities: MutableCollection<out GrantedAuthority>? = null
): AbstractAuthenticationToken(authorities)
{
    override fun getCredentials() = password

    override fun getPrincipal() = email
}
