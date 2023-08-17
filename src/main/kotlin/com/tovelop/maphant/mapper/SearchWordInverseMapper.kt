package com.tovelop.maphant.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface SearchWordInverseMapper {
    fun findByWordId(wordId:Int): SearchWordInverseMapper?
}