package com.tovelop.maphant.service

import com.tovelop.maphant.dto.AdminBoardReportDTO
import com.tovelop.maphant.mapper.AdminPageMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminPageService(@Autowired val adminPageMapper: AdminPageMapper, @Autowired val userService: UserService) {
    fun updateUserState(email: String, state: Int){
        userService.updateUserState(email, state)
    }
    fun updateUserRole(role: String, id: Int) {
        userService.updateUserRole(role, id)
    }

    /**
     * sortType: 정렬 기준 (reportedAt: 오래된 신고부터, mostReportedRanking: 신고를 많이 받은 순서로)
     * reportSize: 불러올 신고의 개수.
     */
    fun findBoardReport(sortType: String, reportSize: Int): List<AdminBoardReportDTO> {
        return when(sortType){
            "reportedAt" -> adminPageMapper.findBoardReportByReportedAt(reportSize)
            "mostReportedRanking" -> adminPageMapper.findBoardReportByMostReportedRanking(reportSize)
            else -> adminPageMapper.findBoardReportByMostReportedRanking(reportSize)
        }
    }
//    fun insertBoardSanction(boardId: Int) {
//        adminPageMapper.setBoardSanction(boardId)
//    }
//    fun findCommentReport() {
//        adminPageMapper.findBoardReport()
//    }
//    fun insertCommentSanction(commentId: Int) {
//        adminPageMapper.setBoardSanction(commentId)
//    }
//    fun setUserSanction(userId: Int) { //sanction = 제재
//        adminPageMapper.setUserSanction(userId)
//    }
}
