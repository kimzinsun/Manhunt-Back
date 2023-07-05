package com.tovelop.maphant.configure.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncoderConfig {

    @Bean
    fun passwordEncoderSHA512(): PasswordEncoder {
        return PasswordEncoderSHA512()
    }

    @Bean
    fun passwordEncoderSHA256(): PasswordEncoderSHA256 {
        return PasswordEncoderSHA256()
    }

    @Bean
    fun passwordEncoderBcrypt(): PasswordEncoder {
        return PasswordEncoderBcrypt()
    }
}
