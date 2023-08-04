package com.tovelop.maphant.service

import org.springframework.beans.factory.annotation.Autowired

class RateLimitingService(@Autowired val redisService: RedisService) {
    fun requestCheck(userId: String, requestType: String){
        val incValue = when(requestType){
            "WRITE_POST" -> 2
            "WRITE_COMMENT" -> 1
            else -> 0
        }
        val key = "WRITE_REQUEST|$userId"
        val value = redisService.get(key)?.toIntOrNull() ?: 0 // 요청 횟수
        redisService.set(key, (value+incValue).toString())
        if (value == 1 && requestType == "WRITE_COMMENT"){ //댓글 첫 요청시 1분간 유효
            redisService.expire(key, 60)
        }
        else if(value == 2 && requestType == "WRITE_POST"){ //글쓰기 첫 요청시 1분간 유효
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
    fun isBanned(userId: String): Boolean{
        val banKey = "BAN|$userId"
        return redisService.get(banKey) != null
    }
}