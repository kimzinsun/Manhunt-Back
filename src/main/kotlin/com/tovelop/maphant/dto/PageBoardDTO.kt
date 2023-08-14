package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class PageBoardDTO(
    val boardId: Int,
    val title: String,
    val userId: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
    val userNickname: String,
    val commentCnt: Int,
    val likeCnt: Int,
    val isAnonymous: Int,
    val isHide: Int,
    val isLike: Int?,
    val imagesUrl: String? = null

) {
    fun toUpgradePageBoardDTO(loginId: Int): UpgradePageBoardDTO {
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
            isMyArticle = userId == loginId
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
    val isLike: Int?,
    val imagesUrl: List<String>? = null,
    val isMyArticle: Boolean
)