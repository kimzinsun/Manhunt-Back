package com.tovelop.maphant.service

import com.tovelop.maphant.dto.*
import com.tovelop.maphant.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BoardService(@Autowired val boardMapper: BoardMapper) {
    fun getBoardTypeIdByBoardTypeName(boardTypeName: String): Int {
        return boardMapper.getBoardTypeIdByBoardTypeName(boardTypeName)
    }
    fun getCategoryIdByCategoryName(categoryName: String): Int {
        return boardMapper.getCategoryIdByCategoryName(categoryName)
    }

    fun findBoardList(findBoardDTO: FindBoardDTO, userId: Int, categoryId: Int): List<PageBoardDTO> {
        val startRow = (findBoardDTO.page - 1) * findBoardDTO.pageSize
        val boardTypeId = boardMapper.getBoardTypeIdByBoardTypeName(findBoardDTO.boardType)
        return boardMapper.findBoardList(findBoardDTO, startRow, categoryId, boardTypeId);
    }
    fun insertBoard(boardDTO: BoardDTO) {
        boardMapper.insertBoard(boardDTO)
    }

    fun findBoard(boardId: Int, userId: Int): ExtBoardDTO? {
        return boardMapper.findBoard(boardId)?.toExtBoardDTO(findBoardLike(boardId, userId))
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
        val isModified = boardMapper.isModified(boardId)
        return isModified!=null
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
    fun isInCategory(categoryId: Int): Boolean {
        return boardMapper.isInCategory(categoryId) != null
    }
    fun isInBoardtype(boardType: String): Boolean {
        return boardMapper.isInBoardtype(boardType) != null
    }

    fun findBoardLike(boardId: Int, userId: Int): Boolean{
        val boardLikeDTO = boardMapper.findBoardLike(boardId, userId)
        return boardLikeDTO!=null
    }
}


