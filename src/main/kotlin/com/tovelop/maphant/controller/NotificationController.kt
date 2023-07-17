package com.tovelop.maphant.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tovelop.maphant.dto.NotificationResponseDTO
import com.tovelop.maphant.service.NotificationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notifications")
class NotificationController(private val notificationService: NotificationService) {
    private val objectMapper = ObjectMapper()

    @GetMapping("/{userId}")
    fun getNotificationsByUserId(@PathVariable userId: Int): List<NotificationResponseDTO> {
        return notificationService.getNotificationsByUserId(userId)
    }
}
