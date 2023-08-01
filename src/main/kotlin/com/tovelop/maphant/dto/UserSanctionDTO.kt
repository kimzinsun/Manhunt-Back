package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class UserSanctionDTO (
    val userId: Int,
    val userNickname: String,
    val userEmail: String,
    val deadlineAt: LocalDateTime,
    val sanctionReason: String
)