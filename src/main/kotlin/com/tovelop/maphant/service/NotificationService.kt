package com.tovelop.maphant.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tovelop.maphant.dto.FcmMessageDTO
import com.tovelop.maphant.dto.NotificationDBDTO
import com.tovelop.maphant.dto.NotificationResponseDTO
import com.tovelop.maphant.mapper.NotificationMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NotificationService(
    private val notificationMapper: NotificationMapper
) {

    fun getNotificationsByUserId(userId: Int): List<NotificationResponseDTO> {
        val result = notificationMapper.getNotificationsByUserId(userId)
        return result.map {
            NotificationResponseDTO(
                id = it.id,
                etc = ObjectMapper().readValue(it.etc ?: ""),
                title = it.title,
                body = it.body,
                createdAt = it.createdAt,
                readAt = it.readAt
            )
        }
    }

    fun createNotification(notification: FcmMessageDTO) {
        val data: Map<String, String>? = notification.etc
        // convert map to json string
        val jsonStr = ObjectMapper().writeValueAsString(data)

        notificationMapper.createNotification(notification, jsonStr)
    }

    fun updateNotification(id: Int) {
        notificationMapper.updateNotification(id)
    }
}