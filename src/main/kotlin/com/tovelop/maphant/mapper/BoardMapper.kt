package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.*
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BoardMapper {
    fun createBoard(boardDTO: BoardDTO): Unit
    fun findBoard(boardId: Int): BoardDTO?
    fun updateBoard(updateBoardDTO: UpdateBoardDTO)
    fun deleteBoard(boardId: Int): Unit
    fun getIsHideByBoardId(boardId: Int): Int?
    fun getUserIdByBoardId(boardId: Int): Int?
    fun isModified(boardId: Int): BoardTimeDTO
    fun insertBoardLike(boardId: Int, userId: Int)
    fun deleteBoardLike(boardId: Int, userId: Int)
    fun insertBoardReport(boardId: Int, userId: Int, reportId: Int)
    fun findBoardByKeyword(keyword: String): List<BoardDTO>
    fun findBoardList(findBoardDTO: FindBoardDTO): List<PageBoardDTO>
}
