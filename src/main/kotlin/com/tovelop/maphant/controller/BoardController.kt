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
    fun readBoard(): ResponseEntity<ResponseUnit> {
        // 보드 메인 (선택한 장르의 게시글)
        // 정렬(추천수, 생성 일자)
        // 추천수, 작성자(익명인지), 수정 일자, 제목,
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
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
        // 게시글 삭제
        // 관리자 권한 확인
        // 본인 게시글 인지 확인
        boardService.deletePost(post.postId)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/create")
    fun createPost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 제목 내용 빈칸인지 확인

        boardService.createPost(post)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PutMapping("/update")
    fun updatePost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 제목 내용 빈칸인지 확인
        boardService.updatePost(post)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/search")
    fun searchPost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 검색어가 포함된 게시글 읽어오기
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }
    @GetMapping("/category")
    fun readCategory(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 장르별 게시글 읽어오기
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }
    @GetMapping("/my")
    fun readMyPost(@RequestBody post: BoardDTO): ResponseEntity<ResponseUnit> {
        // 내가 쓴 게시글 읽어오기

        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

}
