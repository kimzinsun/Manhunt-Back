package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.MockupCustomUserToken
import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.ProfileImageDto
import com.tovelop.maphant.dto.RequestSendDmDto
import com.tovelop.maphant.service.DmService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dm")
class DmController(private val dmService: DmService) {
    @PostMapping
    fun sendDM(@RequestBody @Validated requestSendDmDto: RequestSendDmDto): SuccessResponse<String> {
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userNickname = auth.getUserData().nickname
        val userId: Int = auth.getUserId()!!

        // 서비스 호출
        dmService.sendDm(userNickname,userId, requestSendDmDto.receiver_id as Int, requestSendDmDto.content as String)
        return SuccessResponse("메시지를 전송하였습니다.")
    }

    @GetMapping("/unread/count")
    fun findAllUnreadDm(): ResponseEntity<Response<Int>> {
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId: Int = auth.getUserId()!!

        return ResponseEntity.ok().body(Response.success(dmService.findUnReadDmCount(userId)));
    }
}