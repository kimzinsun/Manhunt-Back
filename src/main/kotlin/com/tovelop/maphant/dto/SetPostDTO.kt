package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class SetPostDTO(
    val parentId: Int?,
    val categoryId: Int,
    val userId: Int,
    val typeId: Int,
    val title: String,
    val body: String,
    val isHide: Boolean,
    val isComplete: Boolean,
    val isAnonymous: Boolean
) {
    fun toBoardDTO(): BoardDTO {
        return BoardDTO(
            parentId = parentId,
            categoryId = categoryId,
            userId = userId,
            typeId = typeId,
            title = title,
            body = body,
            state = 0,
            isHide = isHide,
            isComplete = isComplete,
            isAnonymous = isAnonymous,
            createAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
            commentCnt = 0,
            likeCnt = 0,
            reportCnt = 0,
            imagesUrl = null
        )
    }
}
