package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.BoardTimeDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BoardMapper {
    fun createPost(boardDTO: BoardDTO): Unit
    fun readPost(postId: Int): BoardDTO?
    fun updatePost(boardDTO: BoardDTO)
    fun deletePost(postId: Int): Unit
    fun getIsHideByPostId(postId: Int): Int?
    fun getUserIdByPostId(postId: Int): Int?
    fun isModified(postId: Int): BoardTimeDTO
}
