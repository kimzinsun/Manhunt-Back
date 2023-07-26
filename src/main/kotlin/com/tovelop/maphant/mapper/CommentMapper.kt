package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.dto.CommentLikeDTO
import com.tovelop.maphant.dto.CommentReportDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface CommentMapper {
    fun findAllComment(boardId: Int): List<CommentDTO>
    fun insertComment(commentDTO: CommentDTO)
    fun deleteComment(userId: Int, commentId: Int)
    fun updateComment(commentDTO: CommentDTO)
    fun insertCommentLike(userId: Int, commentId: Int)
    fun findCommentLike(userId: Int, commentId: Int): List<CommentLikeDTO>?
    fun cntCommentLike(commentId: Int): Int
    fun deleteCommentLike(userId: Int, commentId: Int)
    fun insertCommentReport(userId: Int, commentId: Int, reportId: Int)
    fun findCommentReport(userId: Int, commentId: Int): List<CommentReportDTO>?
    fun getCommentById(commentId: Int): CommentDTO?
}