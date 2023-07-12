package com.tovelop.maphant.dto

import com.google.api.client.json.Json
import java.util.Date

data class AlertLogDTO(
    val id: Int,
    val user_id: Int,
    val title: String,
    val body: String,
    val etc: Json,
    val created_at: Date,
)