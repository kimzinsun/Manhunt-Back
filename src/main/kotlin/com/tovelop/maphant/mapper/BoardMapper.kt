package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Mapper
@Repository
interface BoardMapper {
    fun insertBoard(board: BoardDTO)
    fun updateBoard(board: BoardDTO)
    fun deleteBoard(boardId: Int)
    fun getBoardById(boardId: Int): BoardDTO?
    fun getAllBoards(): List<BoardDTO>
    fun getBoardsByCategoryId(categoryId: Int): List<BoardDTO>
    fun getBoardsByUserId(userId: Int): List<BoardDTO>

}
