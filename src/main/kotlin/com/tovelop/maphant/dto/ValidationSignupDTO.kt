package com.tovelop.maphant.dto

data class ValidationSignupDTO(
    val email: String? = null,
    val nickname: String? = null,
    val phoneNum: String? = null,
    val password: String? = null,
    val passwordCheck: String? = null,
    val category: String? = null,
    val major: String? = null
)
