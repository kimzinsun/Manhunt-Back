package com.tovelop.maphant.configure.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PasswordEncoderSHA256Test {
    val obj = PasswordEncoderSHA256()

    @Test
    @DisplayName("SHA256 Hello")
    fun test1() {
        val pw = "Hello"
        obj.SHA256(pw)
        println(obj.SHA256(pw))
    }

    @Test
    @DisplayName("SHA256 안녕")
    fun test2() {
        val pw = "안녕"
        obj.SHA256(pw)
        println(obj.SHA256(pw))
    }
}