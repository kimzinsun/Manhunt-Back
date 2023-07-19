package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BoardMapper {
    fun createPost(boardDTO: BoardDTO)
    fun readPost(postId: Int): BoardDTO
    fun updatePost(boardDTO: BoardDTO)
    fun deletePost(postId: Int)
    fun getIsHideByPostId(postId: Int): Int
    fun getUserIdByPostId(postId: Int): Int
}
