package com.tovelop.maphant.configure.security

import com.tovelop.maphant.dto.MockupUserDTO
import com.tovelop.maphant.dto.UserDTO
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserData(
    private val email: String,
    private val password: String,
    private val userData: MockupUserDTO,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword() = password

    override fun getUsername() = email

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getUserData() = this.userData

    fun getUserID() = this.userData.id

    fun zeroisePassword() {
        this.userData.password = ""
    }
}