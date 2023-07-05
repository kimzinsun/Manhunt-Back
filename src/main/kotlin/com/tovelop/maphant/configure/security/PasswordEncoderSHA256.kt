package com.tovelop.maphant.configure.security

import java.security.MessageDigest

class PasswordEncoderSHA256 {
    fun SHA256(pw: String): String {
        val bytes = pw.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}