package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.SearchWordDto
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface SearchWordMapper {

    fun findSearchWordListByWord(words:List<String>): List<SearchWordDto>?
}