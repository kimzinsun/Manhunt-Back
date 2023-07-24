package com.tovelop.maphant.dto

data class UpdateBoardDTO(
    val id: Int,
    val title: String,
    val body: String,
    val isHide: Int
)
