package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.mapper.BoardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BoardService(@Autowired val boardMapper: BoardMapper) {
    fun createPost() {

    }

    fun readPost(postId: Int)  {


    }

    fun updatePost(boardDTO: BoardDTO) {


    }

    fun deletePost(postId: Int) {

    }


}


