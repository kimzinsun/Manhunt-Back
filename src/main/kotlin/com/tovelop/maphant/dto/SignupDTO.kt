package com.tovelop.maphant.dto

import java.time.LocalDate

data class SignupDTO (
    val email: String,
    val password: String,
    val passwordChk: String,
    val nickname: String,
    val name: String,
    val sNo: Long,
    val phoneNo: String
)