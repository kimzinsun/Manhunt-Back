package com.tovelop.maphant.configure.security

import com.tovelop.maphant.service.RedisService
import com.tovelop.maphant.utils.RandomGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class TokenRepository(@Autowired private val redis: RedisService) {
    fun generateToken(userData: UserDetails): Pair<String, String> {
        val privKey = RandomGenerator.generateRandomString(64)
        val pubKey = generateUniqueToken(privKey)

        redis.set(pubKey, "${redis.get(pubKey)}|${userData}")

        return Pair(pubKey, privKey)
    }

    fun generateUniqueToken(testAndSet: String): String {
        while(true) {
            val key = RandomGenerator.generateRandomString(48)

            if(redis.setnx(key, testAndSet)) {
                return key
            }
        }
    }
}