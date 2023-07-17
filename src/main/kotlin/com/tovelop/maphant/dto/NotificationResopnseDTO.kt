package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class NotificationDBDTO(
    val id: Int,
    val etc: String,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime
)

data class NotificationResponseDTO(
    val id: Int,
    val etc: Map<*, *>,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime
)