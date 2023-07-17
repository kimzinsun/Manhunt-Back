package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class NotificationResponseDTO(
    val id: Int,
    val title: String,
    val body: String,
    val data: Map<String, String>,
    val createdAt: LocalDateTime,
)