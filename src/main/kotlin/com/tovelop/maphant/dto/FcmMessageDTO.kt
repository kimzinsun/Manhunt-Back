package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class FcmMessageDTO(
    val userId: Int,
    val to: List<String>? = null, // receiver's-fcm-token
    val title: String,
    val body: String,
    val etc: Map<String, String>, // send more key-value (ex. chatting service - sender, message, etc...)*/
)