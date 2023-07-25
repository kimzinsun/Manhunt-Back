package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.*
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Mapper
@Repository
interface BoardMapper {
    fun getBoardTypeIdByBoardTypeName(boardTypeName: String): Int
    fun getCategoryIdByCategoryName(categoryName: String): Int
    fun findBoardList(findBoardDTO: FindBoardDTO, startRow: Int, categoryId: Int, boardTypeId: Int): List<PageBoardDTO>
    fun insertBoard(boardDTO: BoardDTO): Unit
    fun findBoard(boardId: Int): BoardDTO?
    fun updateBoard(updateBoardDTO: UpdateBoardDTO)
    fun deleteBoard(boardId: Int): Unit
    fun getIsHideByBoardId(boardId: Int): Int?
    fun getUserIdByBoardId(boardId: Int): Int?
    fun isModified(boardId: Int): LocalDateTime
    fun insertBoardLike(boardId: Int, userId: Int)
    fun deleteBoardLike(boardId: Int, userId: Int)
    fun insertBoardReport(boardId: Int, userId: Int, reportId: Int)
    fun findBoardByKeyword(keyword: String): List<BoardDTO>
}
