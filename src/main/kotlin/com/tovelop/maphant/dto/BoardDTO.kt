package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class BoardDTO(
    val parentId: Int?,
    val categoryId: Int,
    val userId: Int,
    val typeId: Int,
    val title: String,
    val body: String,
    val state: Int,
    val isHide: Int,
    val isComplete: Int,
    val isAnonymous: Int,
    val commentCnt: Int,
    val likeCnt: Int,
    val reportCnt: Int,
    val imagesUrl: String?
)
