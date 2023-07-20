package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.CommentDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface CommentMapper {
    fun findAllComment(boardId: Int): List<CommentDTO>
    fun insertComment(commentDTO: CommentDTO)
    fun deleteComment(commentId: Int)
    fun updateComment(commentDTO: CommentDTO)
    fun cntComment(boardId: Int): Int
    fun insertCommentLike(userId: Int, commentId: Int)
    fun findCommentLike(userId: Int, commentId: Int): Int
    fun cntCommentLike(commentId: Int): Int
    fun deleteCommentLike(userId: Int, commentId: Int)
    fun insertCommentReport(userId: Int, commentId: Int, reportReason: String)
    fun findCommentReport(userId: Int, commentId: Int): Int
    fun cntCommentReport(commentId: Int): Int
}