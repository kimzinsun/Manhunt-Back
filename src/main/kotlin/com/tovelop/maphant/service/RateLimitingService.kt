package com.tovelop.maphant.service

import org.springframework.beans.factory.annotation.Autowired

class RateLimitingService(@Autowired val redisService: RedisService) {
    fun requestCheck(userId: String){
        val key = "WRITE_REQUEST|$userId"
        val value = redisService.get(key)?.toIntOrNull() ?: 0 // 요청 횟수
        redisService.set(key, (value+1).toString())
        if (value == 1){ //첫 요청시 1분간 유효
           // redisService.expire(key, 60)
        }
        // 1분간 요청이 5회 이상이면 밴
        if (value >= 5){
            //redisService.set("BAN|$userId", "true")
            //redisService.expire("BAN|$userId", 60*60*24)
        }
    }
}