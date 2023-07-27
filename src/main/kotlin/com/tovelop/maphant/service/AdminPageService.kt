package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.AdminPageMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AdminPageService(@Autowired val adminPageMapper: AdminPageMapper, @Autowired val userService: UserService) {

    
    //추후 수정
    fun updateUserState(email: String, state: Int){
        userService.updateUserState(email, state)
    }
    fun setUserRole(role: String, id: Int) {
        adminPageMapper.setUserRole(role, id)
    }
    fun findBoardReport(): List<BoardDTO> {
        return adminPageMapper.findBoardReport()
    }
    fun insertBoardSanction(boardId: Int) {
        adminPageMapper.setBoardSanction(boardId)
    }
    fun findCommentReport(): List<CommentDTO> {
        return adminPageMapper.findCommentReport()
    }
    fun insertCommentSanction(commentId: Int) {
        adminPageMapper.setCommentSanction(commentId)
    }
    fun findUserReportByUserid(userId: Int): UserDTO { //sanction = 제재
        return adminPageMapper.findUserReportByUserid(userId)
    }
    fun setUserSanction(userId: Int, deadlineAt: LocalDateTime, sanctionReason: String) {
        adminPageMapper.setUserSanction(userId, deadlineAt, sanctionReason)
    }
}
