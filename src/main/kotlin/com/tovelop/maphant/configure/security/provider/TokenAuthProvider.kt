package com.tovelop.maphant.configure.security.provider

import com.tovelop.maphant.configure.security.UserData
import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.service.RedisService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TokenAuthProvider(
    private val redis: RedisService
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        if(authentication.isAuthenticated) return authentication

        val token = authentication as TokenAuthToken

        if(token.isExpired()) {
            throw SecurityException("Token expired")
        }

        val principal = token.principal
        val credentials = token.credentials

        val userToken = redis.get(principal) ?: throw SecurityException("No user")
        val user = userToken.split('|')

        if(credentials.third != token.createToken(credentials.second, user[0])) {
            throw SecurityException("Invalid token")
        }

        println(user[1])

        val userData = UserData("test@test.com", "1234", UserDTO(
            id = 1,
            email = "test@test.com",
            password = "1234",
            nickname = "NickName",
            name = "User Name",
            phoneInt = "1234567890",
            sNo = "2017648070",
            create_at = LocalDate.now(),
            role = "user",
            state = "1",
            is_agree = "Yes",
            last_modified_date = LocalDate.now(),
            university_id = 123
        ))

        return TokenAuthToken(principal, credentials.second, credentials.third, userData)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        if(authentication == null) return false

        return authentication == TokenAuthToken::class.java
    }
}