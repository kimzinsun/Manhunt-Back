package com.tovelop.maphant.dto

import java.time.LocalDateTime

data class AdminBoardReportDTO(
    val boardId: Int,
    val boardTitle: String,
    val boardBody: String,
    val boardUserId: Int,
    val boardUserEmail: String,
    val reporterUserId: Int,
    val reporterUserEmail: String,
    val boardReportedAt: LocalDateTime,
    val boardReportName: String
)
//    글 id, title, body/ 작성자 id, email / 신고자 id, email / 신고 시간, 사유
