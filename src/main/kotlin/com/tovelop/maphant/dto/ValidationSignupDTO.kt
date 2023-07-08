package com.tovelop.maphant.dto

data class ValidationSignupDTO(
    val email: String? = null,
    val nickName: String? = null,
    val phoneNum: String? = null,
    val password: String? = null,
    val passwordChk: String? = null
)
