package com.tovelop.maphant.dto

data class SetPostDTO(
    val id: Int,
    val parentId: Int?,
    val categoryId: Int,
    val userId: Int,
    val typeId: Int,
    val title: String,
    val body: String,
    val isHide: Byte,
    val isComplete: Byte,
    val isAnonymous: Byte,
    )