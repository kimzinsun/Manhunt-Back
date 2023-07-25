package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.FindBoardDTO
import com.tovelop.maphant.dto.PageBoardDTO
import com.tovelop.maphant.dto.UpdateBoardDTO
import com.tovelop.maphant.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BoardService(@Autowired val boardMapper: BoardMapper) {
    fun findBoardList(findBoardDTO: FindBoardDTO): List<PageBoardDTO> {
        val startRow = (findBoardDTO.page - 1) * findBoardDTO.pageSize
        return boardMapper.findBoardList(findBoardDTO, startRow);
    }
    fun insertBoard(boardDTO: BoardDTO) {
        boardMapper.insertBoard(boardDTO)
    }

    fun findBoard(boardId: Int): BoardDTO? {
        return boardMapper.findBoard(boardId)
    }

    fun updateBoard(updateBoardDTO: UpdateBoardDTO) {
        boardMapper.updateBoard(updateBoardDTO)
    }

    fun deleteBoard(boardId: Int) {
        boardMapper.deleteBoard(boardId)
    }

    fun getIsHideByBoardId(boardId: Int): Boolean? {
        val isHide = boardMapper.getIsHideByBoardId(boardId)
        return isHide==1
    }

    fun getUserIdByBoardId(boardId: Int): Int?{
        return boardMapper.getUserIdByBoardId(boardId)
    }
    fun isModified(boardId: Int): Boolean {
        val boardTimeDTO = boardMapper.isModified(boardId)
        return boardTimeDTO.createAt == boardTimeDTO.modifiedAt
    }
    fun insertBoardLike(boardId: Int, userId: Int) {
        boardMapper.insertBoardLike(boardId, userId)
    }
    fun deleteBoardLike(boardId: Int, userId: Int) {
        boardMapper.deleteBoardLike(boardId, userId)
    }
    fun insertBoardReport(boardId: Int, userId: Int, reportId: Int) {
        boardMapper.insertBoardReport(boardId, userId, reportId)
    }
    fun findBoardByKeyword(keyword: String): List<BoardDTO> {
        return boardMapper.findBoardByKeyword(keyword)
    }
}


