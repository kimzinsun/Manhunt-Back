package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class BoardDTO(
    val categoryId: Int,
    val userId: Int,
    val postId: Int,
    val type: String,
    val title: String,
    val body: String,
    val state: String,
    val isAnonymous: Boolean,
    val createAt: LocalDateTime,
    val modifiedAt: LocalDateTime,


    )