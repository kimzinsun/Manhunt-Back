package com.tovelop.maphant.dto

data class ProfileNicknameAndBodyAndImageDto(
    val user_nickname: String,
    val category_name: String?,
    val major_name: String?,
    val body: String?,
    val profile_img: String?,
)
