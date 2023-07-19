package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class BoardDTO(
    val id: Int,
    val parentId: Int?,
    val categoryId: Int,
    val userId: Int,
    val type: Int,
    val title: String,
    val body: String,
    val state: Int,
    val isHide: Boolean,
    val isComplete: Boolean,
    val isAnonymous: Boolean,
    val createAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
    val commentCount: Int,
    val likeCount: Int,
    val reportCount: Int,
    )