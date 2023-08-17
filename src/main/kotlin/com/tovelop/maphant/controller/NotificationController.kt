package com.tovelop.maphant.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.BoardResDto
import com.tovelop.maphant.dto.NotificationDBDTO
import com.tovelop.maphant.dto.NotificationResponseDTO
import com.tovelop.maphant.service.NotificationService
import com.tovelop.maphant.type.paging.PagingDto
import com.tovelop.maphant.type.paging.PagingResponse
import com.tovelop.maphant.type.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/notifications")
class NotificationController(private val notificationService: NotificationService) {

    @GetMapping("/")
    fun getNotifications(
        @ModelAttribute pagingDto: PagingDto,
    ): ResponseEntity<Response<PagingResponse<NotificationResponseDTO>>> {
        val auth = SecurityContextHolder.getContext().authentication as TokenAuthToken
        return ResponseEntity.ok().body(Response.success(notificationService.getNotificationsByUserId(auth.getUserId(), pagingDto)))
    }

//    @GetMapping("/{userId}")
//    fun getNotificationsByUserId(@PathVariable userId: Int): List<NotificationResponseDTO> {
//        return notificationService.getNotificationsByUserId(userId)
//    }

    @GetMapping("{userId}/{notificationId}")
    fun updateNotification(
        @PathVariable userId: Int,
        @PathVariable notificationId: Int
    ) {
        notificationService.updateNotification(notificationId)
    }
}
