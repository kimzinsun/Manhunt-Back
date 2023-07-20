package com.tovelop.maphant.dto

import java.time.LocalDate

data class UserDTO(
    val id: Int? = null,
    val email: String,
    var password: String,
    val nickname: String,
    val name: String,
    val phoneNum: String?,
    val sNo: String,
    val role: String,
    val state: String,
    val createdAt: LocalDate,
    val agreedAt: LocalDate,
    val lastmodifiedAt: LocalDate,
    val univId: Int?
)
