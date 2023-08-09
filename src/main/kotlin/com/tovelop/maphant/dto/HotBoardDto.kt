package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class HotBoardDto(
    val boardId : Int,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val modifiedAt : LocalDateTime?,
    val userNickname: String,
    val commentCnt: Int,
    val likeCnt: Int,
    val isAnonymous: Int,
    val isHide: Int,
    var isLike: Boolean,
    val imagesUrl: String? = null,
    private val tagStrings: String?,
    val typeId: Int,
    val type: String,
) {
    val tags: List<String>
        get() = tagStrings?.split(",") ?: listOf()
}
