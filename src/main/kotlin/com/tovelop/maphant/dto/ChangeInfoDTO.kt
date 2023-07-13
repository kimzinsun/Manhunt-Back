package com.tovelop.maphant.dto

data class ChangeInfoDTO (
    val email: String,
    val nickname: String,
    val nowPassword: String,
    val password: String,
    val passwordChk: String,
    val phoneNo: String? = null

)