package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class PageBoardDTO(
    val boardId: Int,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
    val userNickname: String,
    val commentCnt: Int,
    val likeCnt: Int,
    val isAnonymous: Int,
    val isHide: Int,
    var isLike: Boolean,
    val imagesUrl: String? = null,
    private val tagStrings: String?
) {
    val tags: List<String>
        get() = tagStrings?.split(",") ?: listOf()

    fun toUpgradePageBoardDTO(): UpgradePageBoardDTO {
        return UpgradePageBoardDTO(
            boardId = boardId,
            title = title,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            userNickname = userNickname,
            commentCnt = commentCnt,
            likeCnt = likeCnt,
            isAnonymous = isAnonymous,
            isHide = isHide,
            isLike = isLike,
            imagesUrl = imagesUrl?.split(","),
            tags = tags
        )
    }
}

data class UpgradePageBoardDTO(
    val boardId: Int,
    val title: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
    val userNickname: String,
    val commentCnt: Int,
    val likeCnt: Int,
    val isAnonymous: Int,
    val isHide: Int,
    val isLike: Boolean,
    val imagesUrl: List<String>? = null,
    val tags: List<String>
)
