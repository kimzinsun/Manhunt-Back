package com.tovelop.maphant.configure.security

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Primary
@Component
class PasswordEncoderBcrypt : BCryptPasswordEncoder() {
}