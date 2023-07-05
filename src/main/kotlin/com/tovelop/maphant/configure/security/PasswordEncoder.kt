package com.tovelop.maphant.configure.security

import org.springframework.stereotype.Component


@Component
class PasswordEncoder{
    fun getPassword(str: String): String {
        return str
    }
}