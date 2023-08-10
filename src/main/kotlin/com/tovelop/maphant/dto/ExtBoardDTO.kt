package com.tovelop.maphant.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class ExtBoardDTO(
    val id: Int?,
    @JsonIgnore
    val userId: Int,
    val parentId: Int?,
    val categoryId: Int,
    val userNickname: String,
    val typeId: Int,
    val title: String,
    val body: String,
    val state: Int,
    val isHide: Int,
    val isComplete: Int,
    val isAnonymous: Int,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    val commentCnt: Int,
    val likeCnt: Int,
    val reportCnt: Int,
    val imagesUrl: String?,
    val isLike: Boolean,
    val isMyBoard: Boolean,
    var tags: List<ReqTagDTO>
) {
    constructor(
        id: Int?,
        userId: Int,
        parentId: Int?,
        categoryId: Int,
        userNickname: String,
        typeId: Int,
        title: String,
        body: String,
        state: Int,
        isHide: Int,
        isComplete: Int,
        isAnonymous: Int,
        createdAt: LocalDateTime?,
        modifiedAt: LocalDateTime?,
        commentCnt: Int,
        likeCnt: Int,
        reportCnt: Int,
        imagesUrl: String?,
        isLike: Boolean,
        isMyBoard: Boolean,
    ): this(
        id, userId, parentId, categoryId, userNickname, typeId, title, body, state, isHide, isComplete, isAnonymous, createdAt, modifiedAt, commentCnt, likeCnt, reportCnt, imagesUrl, isLike, isMyBoard, listOf()
    )
    fun toUpgradeExtBoardDTO(): UpgradeExtBoardDTO {
        return UpgradeExtBoardDTO(
            id = id,
            parentId = parentId,
            categoryId = categoryId,
            userId = userId,
            typeId = typeId,
            title = title,
            body = body,
            state = state,
            isHide = isHide,
            isComplete = isComplete,
            isAnonymous = isAnonymous,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            commentCnt = commentCnt,
            likeCnt = likeCnt,
            reportCnt = reportCnt,
            imagesUrl = imagesUrl?.split(","),
            isLike = isLike,
            tags = tags
        )
    }
}

data class UpgradeExtBoardDTO(
    val id: Int?,
    val parentId: Int?,
    val categoryId: Int,
    val userId: Int,
    val typeId: Int,
    val title: String,
    val body: String,
    val state: Int,
    val isHide: Int,
    val isComplete: Int,
    val isAnonymous: Int,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    val commentCnt: Int,
    val likeCnt: Int,
    val reportCnt: Int,
    val imagesUrl: List<String>?,
    val isLike: Boolean,
    var tags: List<ReqTagDTO>
)

