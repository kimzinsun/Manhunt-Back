package com.tovelop.maphant.service

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.AdminPageMapper
import org.springframework.beans.factory.annotation.Autowired

class AdminPageService(@Autowired val adminPageMapper: AdminPageMapper) {
    fun isAdmin(userDTO: UserDTO): Boolean {
        if(userDTO.role == "admin") return true
        else return false
//        if (auth.getUserData().role != "admin" || auth.getUserData().id != boardService.getUserIdByBoardId(boardId)) {
//            return ResponseEntity.badRequest().body(Response.error<Any>("권한이 없습니다."))
//        }
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