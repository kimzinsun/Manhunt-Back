package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.BoardTimeDTO
import com.tovelop.maphant.dto.UpdateBoardDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BoardMapper {
    fun createBoard(boardDTO: BoardDTO): Unit
    fun readBoard(boardId: Int): BoardDTO?
    fun updateBoard(updateBoardDTO: UpdateBoardDTO)
    fun deleteBoard(boardId: Int): Unit
    fun getIsHideByBoardId(boardId: Int): Int?
    fun getUserIdByBoardId(boardId: Int): Int?
    fun isModified(boardId: Int): BoardTimeDTO
    fun insertBoardLike(boardId: Int, userId: Int)
    fun deleteBoardLike(boardId: Int, userId: Int)
    fun insertBoardReport(boardId: Int, userId: Int, reportId: Int)
    fun findBoardByKeyword(keyword: String): List<BoardDTO>
}
