package com.tovelop.maphant.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class BoardController {
    @GetMapping("/main")
    fun readBoard() { //
        // 보드 메인 (선택한 장르의 게시글)
        // 정렬(추천수, 생성 일자)
        // 추천수, 작성자(익명인지), 수정 일자, 제목,
        // return: json
    }

    @GetMapping("/boardId")
    fun readPost() {
        // 한개의 게시글 읽어오기
        // 제목, 내용, 댓글, 추천수, 수정 일자, 작성자가 로그인한 사람과 같은지 확인
        // return: json
    }

    @DeleteMapping("/post/delete")
    fun deletePost(@RequestBody postId: String) {
        // 게시글 삭제
        // 게시글 id로 삭제

    }

    @PostMapping("/add")
    fun createPost() {
        // 게시글 추가 (작성자, 생성 시간, 내용)
        // 보드 스키마 다
        // return: json
    }



}