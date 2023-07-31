package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.*
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface AdminPageMapper {
    fun findBoardReportByReportedAt(reportSize: Int): List<AdminBoardReportDTO>
    fun findBoardReportByMostReportedRanking(reportSize: Int): List<AdminBoardReportDTO>
    fun findBoardReportBySortType(reportSize: Int, sortType: String): List<AdminBoardReportDTO>
    fun findBoardReportInfo(boardId: Int): List<BoardReportInfoDTO>
    fun findCommentReportByReportedAt(reportSize: Int): List<AdminCommentReportDTO>
    fun findCommentReportByMostReportedRanking(reportSize: Int): List<AdminCommentReportDTO>
    fun findCommentReportBySortType(reportSize: Int, sortType: String): List<AdminCommentReportDTO>
    fun findCommentReportInfo(commentId: Int): List<CommentReportInfoDTO>
    fun insertUserReport(userReportDTO: UserReportDTO)
//    fun setBoardSanction(boardId: Int)
//    fun findCommentReport()
//    fun setCommentSanction(commentId: Int)
//    fun findUserSanction()
    fun findUserSanction()
}
