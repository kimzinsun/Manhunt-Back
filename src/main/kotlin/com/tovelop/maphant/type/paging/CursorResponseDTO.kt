package com.tovelop.maphant.type.paging

import com.tovelop.maphant.dto.ResultDmDto

class CursorResponseDTO(
    val list: List<ResultDmDto>,
    val otherName: String
) {
}