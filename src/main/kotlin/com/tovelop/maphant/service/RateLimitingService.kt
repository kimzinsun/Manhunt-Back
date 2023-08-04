package com.tovelop.maphant.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RateLimitingService(@Autowired val redisService: RedisService) {
    fun requestCheck(userId: Int, requestType: String){
        val incValue = when(requestType){
            "WRITE_POST" -> 2
            "WRITE_COMMENT" -> 1
            else -> 0
        }
        val key = "WRITE_REQUEST|$userId"
        val value = redisService.get(key)?.toIntOrNull() ?: 0 // 요청 횟수
        redisService.setKeepTTL(key, (value + incValue).toString())
        if (value == 0) {
            // 댓글, 글 첫 요청시 1분간 유효 처리
            redisService.expire(key, 60)
        }
        // 1분간 요청이 댓글 , 글 포함해서 10번 이상이면 1일간 차단
        if (value >= 10){
            val banKey = "BAN|$userId"
            if (redisService.get(banKey) == null){
                redisService.set(banKey, "true")
                redisService.expire(banKey, 60*60*24)
            }
        }
    }
    fun isBanned(userId: Int): Boolean{
        val banKey = "BAN|$userId"
        return redisService.get(banKey) != null
    }
}