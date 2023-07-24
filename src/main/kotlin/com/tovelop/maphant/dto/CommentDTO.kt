package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class CommentDTO(
    val id: Int,
    val user_id: Int,
    val board_id: Int,
    val body: String,
    val is_anonymous: Boolean,
    val created_at: LocalDateTime,
    val like_cnt: Int,
)