package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.UpdatePostDTO
import com.tovelop.maphant.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BoardService(@Autowired val boardMapper: BoardMapper) {
<<<<<<< HEAD
    fun createPost(postBoardDTO: Any) {
//        val board = BoardDTO()
//        return boardMapper.createPost(board)
=======
    fun createPost(boardDTO: BoardDTO) {
        boardMapper.createPost(boardDTO)
>>>>>>> feat-board
    }

    fun readPost(postId: Int): BoardDTO? {
        return boardMapper.readPost(postId)
    }

    fun updatePost(updatePostDTO: UpdatePostDTO) {
        boardMapper.updatePost(updatePostDTO)
    }

    fun deletePost(postId: Int) {
        boardMapper.deletePost(postId)
    }

    fun getIsHideByPostId(postId: Int): Boolean? {
        val isHide = boardMapper.getIsHideByPostId(postId)
        return isHide==1
    }

    fun getUserIdByPostId(postId: Int): Int?{
        return boardMapper.getUserIdByPostId(postId)
    }
    fun isModified(postId: Int): Boolean {
        val boardTimeDTO = boardMapper.isModified(postId)
        return boardTimeDTO.createAt == boardTimeDTO.modifiedAt
    }
}


