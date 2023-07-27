package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.dto.CommentExtDTO
import com.tovelop.maphant.dto.CommentLikeDTO
import com.tovelop.maphant.dto.CommentReportDTO
import com.tovelop.maphant.type.paging.PagingDto
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
interface CommentMapper {
    fun findAllComment(boardId: Int, userId: Int, pagingDto: PagingDto): List<CommentExtDTO>
    fun insertComment(commentDTO: CommentDTO)
    fun deleteComment(userId: Int, commentId: Int)
    fun updateComment(commentDTO: CommentDTO)
    fun insertCommentLike(userId: Int, commentId: Int)
    fun findCommentLike(userId: Int, commentId: Int): List<CommentLikeDTO>?
    fun cntCommentLike(commentId: Int): Int
    fun deleteCommentLike(userId: Int, commentId: Int)
    fun insertCommentReport(userId: Int, commentId: Int, reportId: Int)
    fun findCommentReport(userId: Int, commentId: Int, reportId: Int): List<CommentReportDTO>?
    fun getCommentById(commentId: Int): CommentDTO?
    fun getCommentCount(boardId: Int): Int
}