package com.tovelop.maphant.type.paging

import com.tovelop.maphant.dto.ResultDmDto
import jakarta.validation.constraints.Min

data class CursorBasedPagingDTO(
    var result:List<ResultDmDto>,
    var cursor: Int?
)
