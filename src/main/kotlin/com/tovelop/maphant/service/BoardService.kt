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

    fun findPostById(postId: Int):BoardDTO {
        return BoardDTO(1,1,1,"asdfl","asdf","asdf","asdf",true, LocalDateTime.now(), LocalDateTime.now())
    }

    fun updatePost(boardDTO: BoardDTO){
        val postId = boardDTO.postId


    }

    fun deletePost(postId: Int) {

    }


}


