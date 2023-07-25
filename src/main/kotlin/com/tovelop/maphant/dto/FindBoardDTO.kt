package com.tovelop.maphant.dto

data class FindBoardDTO(
    val category: String,
    val boardType: String,
    var page: Int, //페이지 번호
    var pageSize: Int, //한 페이지에 띄울 글 수
    val sortCriterion: String //정렬 기준
)
