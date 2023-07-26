package com.tovelop.maphant.service

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.AdminPageMapper
import com.tovelop.maphant.type.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

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
    fun setBoardReport(boardId: Int) {
        adminPageMapper.setBoardReport(boardId)
    }
    fun findCommentReport() {
        adminPageMapper.findBoardReport()
    }
    fun setCommentReport(commentId: Int) {
        adminPageMapper.setBoardReport(commentId)
    }

}