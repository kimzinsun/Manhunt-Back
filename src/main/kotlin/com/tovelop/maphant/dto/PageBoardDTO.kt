package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class PageBoardDTO(
    val title: String,
    val createdAt: LocalDateTime,
    val userNickname: String,
    val commentCnt: Int,
    val likeCnt: Int
)
