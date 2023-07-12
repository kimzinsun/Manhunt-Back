package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
class BoardMapper {
    fun createPost(boardDTO: BoardDTO) {}
    fun readPost(boardDTO: BoardDTO) {}
    fun updatePost(boardDTO: BoardDTO) {}
    fun deletePost(boardDTO: BoardDTO) {}

}