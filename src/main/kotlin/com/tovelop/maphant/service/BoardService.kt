package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BoardService(@Autowired val boardMapper: BoardMapper) {
    fun createPost(boardDTO: BoardDTO) {
//        val board = BoardDTO()
//        return boardMapper.createPost(board)
    }

    fun readPost(postId: Int)  {
        val post = boardMapper.readPost(postId)
    }

    fun updatePost(boardDTO: BoardDTO) {
        val postId = boardDTO.postId


    }

    fun deletePost(postId: Int) {

    }


}


