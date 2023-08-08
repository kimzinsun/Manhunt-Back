package com.tovelop.maphant.dto

data class TagDTO(
    val id: Int,
    val name: String,
    val category_id: Int,
    val cnt: Int,
)

data class TagInsertDto(
    val categoryId: Int,
    val boardId: Int,
    val tagNames: List<String>,
)