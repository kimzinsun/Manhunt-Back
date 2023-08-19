package com.tovelop.maphant.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface SearchWordMapper {
    fun getWordCnt(word:String):Int
}