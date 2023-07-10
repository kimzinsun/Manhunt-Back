package com.tovelop.maphant.dto

data class FcmDTO(
    val id: Int,
    val user_id: Int,
    val platform: String,
    val device_info: String,
    val fcm_token: String,
)