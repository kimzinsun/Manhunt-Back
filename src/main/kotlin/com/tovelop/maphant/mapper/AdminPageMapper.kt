package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.UserDTO

interface AdminPageMapper {
    fun isAdmin(userDTO: UserDTO): Boolean
    fun setUserRole(role: String, id: Int)
    fun findBoardReport()
    fun setBoardReport(boardId: Int)
    fun findCommentReport()
    fun setCommentReport(commentId: Int)
    fun setUserSanction(userId: Int)
}