package com.tovelop.maphant.controller

import java.time.LocalDateTime

data class PollDTO(
    val id: Int?,
    val boardId: Int,
    val title: String,
    val options: List<String>,
    val expireDateTime: LocalDateTime?
)
