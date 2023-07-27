package com.tovelop.maphant.service

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.AdminPageMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminPageService(@Autowired val adminPageMapper: AdminPageMapper) {
    fun isAdmin(userDTO: UserDTO): Boolean {
        if(userDTO.role == "admin") return true
        else return false

    }
    fun setUserRole(role: String, id: Int) {
        adminPageMapper.setUserRole(role, id)
    }
    fun findBoardReport() {
        adminPageMapper.findBoardReport()
    }
    fun insertBoardSanction(boardId: Int) {
        adminPageMapper.setBoardSanction(boardId)
    }
    fun findCommentReport() {
        adminPageMapper.findBoardReport()
    }
    fun insertCommentSanction(commentId: Int) {
        adminPageMapper.setBoardSanction(commentId)
    }
    fun setUserSanction(userId: Int) { //sanction = 제재
        adminPageMapper.setUserSanction(userId)
    }
}