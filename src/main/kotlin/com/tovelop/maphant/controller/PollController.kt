package com.tovelop.maphant.controller

import com.tovelop.maphant.type.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poll")
class PollController {
    @PostMapping("/") // 투표 생성
    fun createPoll(@RequestBody poll: PollDTO): ResponseEntity<Any> {
        println(poll)

        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/increase")
    fun increaseOption(): ResponseEntity<Any> {
        println("!!!!!!")
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }
}