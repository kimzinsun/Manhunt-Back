package com.tovelop.maphant.type.paging

data class PagingDto(
    var page: Int = 1,
    var recordSize: Int = 10,
    var pageSize: Int = 10,
//    var keyword: String? = null,
//    var searchType: String? = null
) {
    val offset: Int
        get() = (page - 1) * recordSize
}
