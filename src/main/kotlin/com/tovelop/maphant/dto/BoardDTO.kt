package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class BoardDTO(
    val parentId: Int,
    val categoryId: Int,
    val userId: Int,
    val typeId: Int,
    val title: String,
    val body: String,
    val state: Int,
    val isHide: Boolean,
    val isComplete: Boolean,
    val isAnonymous: Boolean,
    val createAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val commentCnt: Int,
    val likeCnt: Int,
    val reportCnt: Int,
    val imagesUrl: String
)
