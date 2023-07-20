package com.tovelop.maphant.dto

data class UpdatePostDTO(
    val id: Int,
    val title: String,
    val body: String,
    val isHide: Int
)
