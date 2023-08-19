package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardSearchResponseDto
import com.tovelop.maphant.dto.SearchWordInverseDto
import com.tovelop.maphant.type.paging.PagingDto
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface SearchWordInverseMapper {
    fun findByWordId(wordId:Int): SearchWordInverseDto?

    fun searchBoardListByWords(wordList:List<String>, userId:Int, categoryId:Int, boardTypeId: Int?, pagingDto: PagingDto): List<BoardSearchResponseDto>
    fun getCountSearchBoardListByWords(wordList:List<String>, categoryId: Int, boardTypeId: Int?): Int
    fun updateIdf(boardCount: Int): Boolean
}