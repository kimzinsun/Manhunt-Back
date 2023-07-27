package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.dto.UserDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Mapper
@Repository
interface AdminPageMapper {
    fun isAdmin(userDTO: UserDTO): Boolean
    fun setUserRole(role: String, id: Int)
    fun findBoardReport(): List<BoardDTO>
    fun setBoardSanction(boardId: Int)
    fun findCommentReport(): List<CommentDTO>
    fun setCommentSanction(commentId: Int)
    fun findUserReportByUserid(userId: Int): UserDTO
    fun setUserSanction(userId: Int, deadlineAt: LocalDateTime, sanctionReason: String)
}
