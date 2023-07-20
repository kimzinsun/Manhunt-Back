package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.type.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
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
        println("slkdfjklsdjf")
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/increase")
    fun selectOption(): ResponseEntity<Any> {
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        

        return ResponseEntity.ok().body(Response.stateOnly(true))
    }
}