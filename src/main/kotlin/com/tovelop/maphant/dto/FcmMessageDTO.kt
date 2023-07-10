package com.tovelop.maphant.dto

data class FcmMessageDTO(
    val to: String, // receiver's-fcm-token
    val notification: NotificationDTO, //title, body
    val data: Map<String, String> = mapOf() // send more key-value
)

data class NotificationDTO(
    val title: String,
    val body: String,
    val clickAction: String? = null
)