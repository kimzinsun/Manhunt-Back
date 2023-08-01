package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class PollInfoDTO(
    val title: String,
    val expireDate: LocalDateTime?,
    val state: Char,
    val selectedOptionId: Int?,
    val pollOptions: List<PollOption>
)

data class PollOption(val optionId: Int, val optionName: String, val optionCount: Int)