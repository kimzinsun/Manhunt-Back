package com.tovelop.maphant.service

import com.tovelop.maphant.configure.security.PasswordEncoder
import com.tovelop.maphant.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class SignService(
    private val encoder: PasswordEncoder,
    private val mapper: UserMapper
)
{
    fun signUp(){
    }
    fun login(){
    }
    fun getUser(user: String): String {
        return user
    }
    fun insertUser(){
    }
    fun isPasswordValid(): Boolean {
        return true
    }
    fun isEmailValid(): Boolean {
        return true
    }
    fun duplicateEmail(): Boolean {
        return true
    }

}