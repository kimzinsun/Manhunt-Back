package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class PageBoardDTO(
    val boardId : String,
    val title: String,
    val createdAt: LocalDateTime,
    val modifiedAt : LocalDateTime,
    val userNickname: String,
    val commentCnt: Int,
    val likeCnt: Int
)
