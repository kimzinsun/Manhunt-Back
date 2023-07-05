package com.tovelop.maphant.configure.security

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.security.MessageDigest

@Component
class PasswordEncoderSHA256 : PasswordEncoder {
    fun SHA256(pw: String): String {
        val bytes = pw.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    override fun encode(rawPassword: CharSequence): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(rawPassword.toString().toByteArray())
        return hash.joinToString(separator = "") { "%02x".format(it) }
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return encode(rawPassword) == encodedPassword
    }
}