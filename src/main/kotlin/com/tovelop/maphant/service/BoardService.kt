package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class BoardService(@Autowired val boardMapper: BoardMapper) {
    fun createPost(boardDTO: BoardDTO) {

    }

    fun findPostById(id: Int):BoardDTO {
        return boardMapper.getBoardById(id)!!
    }

    fun updatePost(boardDTO: BoardDTO){
        val id = boardDTO.id
    }

    fun deletePost(postId: Int) {

    }


}


