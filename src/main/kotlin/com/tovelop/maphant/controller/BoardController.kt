package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.service.BoardService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/board")
class BoardController(@Autowired val boardService: BoardService) {
    @GetMapping("/main")
    fun readBoard() {
        // 보드 메인 (선택한 장르의 게시글)
        // 정렬(추천수, 생성 일자)
        // 추천수, 작성자(익명인지), 수정 일자, 제목,
        // return: json
    }

    @PostMapping("/recommend")
    fun recommendHandle(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 추천수 증가
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/boardId")
    fun readPost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 한 개의 게시글 읽어오기
        // 제목, 내용, 댓글, 추천수, 수정 일자, 작성자가 로그인한 사람과 같은지 확인
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @DeleteMapping("/post/delete")
    fun deletePost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        boardService.deletePost(post.postId)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/create")
    fun createPost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        boardService.createPost(post)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PutMapping("/update")
    fun updatePost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        boardService.updatePost(post)
        return ResponseEntity.ok(Response.stateOnly(true))
    }
}
