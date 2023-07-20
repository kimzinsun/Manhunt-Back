package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.service.PollService
import com.tovelop.maphant.type.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poll")
class PollController(val pollService: PollService) {
    @PostMapping("/") // 투표 생성
    fun createPoll(@RequestBody poll: PollDTO): ResponseEntity<Any> {
        println(poll)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/increase")
    fun selectOption(pollId: Int, pollOption: String): ResponseEntity<Any> {
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken

        val optionResult = pollService.increaseOptionCount(auth.getUserData().id!!, pollId, pollOption)

        if (!optionResult) {
            return ResponseEntity.badRequest().body(
                Response.error<Any>("투표에 실패했습니다")
            )
        }

        return ResponseEntity.ok().body(Response.stateOnly(true))
    }
}