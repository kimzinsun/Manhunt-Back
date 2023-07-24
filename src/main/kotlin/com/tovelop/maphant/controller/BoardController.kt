package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.*
import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.service.BoardService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
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
    fun recommendHandle(@RequestBody post: SetPostDTO): ResponseEntity<ResponseUnit> {
        // 추천수 증가
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/{postId}")
    fun readPost(@PathVariable("postId") postId: Int): ResponseEntity<Any> {
        val auth = SecurityContextHolder.getContext().authentication
        val post = boardService.findBoard(postId)
        if (auth == null || auth !is TokenAuthToken || !auth.isAuthenticated) {
            return ResponseEntity.badRequest().body(Response.error<Any>("로그인 안됨"))
        }

        if (post == null || boardService.getIsHideByBoardId(postId) == null) {
            return ResponseEntity.badRequest().body(Response.error<Any>("게시글이 존재하지 않습니다."))
        }
        if (boardService.getIsHideByBoardId(postId)!!) {
            if (post.userId != auth.getUserData().id) {
                return ResponseEntity.badRequest().body(Response.error<Any>("권한이 없습니다."))
            }
        }

        return ResponseEntity.ok(Response.success(post))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable("postId") postId: Int): ResponseEntity<Any> {
        // 게시글 삭제
        val auth = SecurityContextHolder.getContext().authentication
        if (auth == null || auth !is TokenAuthToken || !auth.isAuthenticated) {
            return ResponseEntity.badRequest().body(Response.error<Any>("로그인 안됨"))
        }
        // 관리자 권한 확인(관리자는 모든 게시글 삭제 가능)
        // 본인 게시글 인지 확인
        if (auth.getUserData().role != "admin" || auth.getUserData().id != boardService.getUserIdByBoardId(postId)) {
            return ResponseEntity.badRequest().body(Response.error<Any>("권한이 없습니다."))
        }
        boardService.deleteBoard(postId)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/create")
    fun createPost(@RequestBody post: SetPostDTO): ResponseEntity<ResponseUnit> {
        // 제목 내용 빈칸인지 확인
        return if (post.title.isNotBlank() && post.body.isNotBlank()) {
            boardService.createBoard(post.toBoardDTO())
            ResponseEntity.ok(Response.stateOnly(true))
        } else {
            ResponseEntity.ok(Response.stateOnly(false)) // 제목 또는 내용이 빈칸인 경우 실패 응답을 반환합니다.
        }
    }

    @PutMapping("/update")
    fun updatePost(@RequestBody post: UpdateBoardDTO): ResponseEntity<ResponseUnit> {
        // 현재 로그인한 사용자 정보 가져오기
        val auth = SecurityContextHolder.getContext().authentication as TokenAuthToken
        // 게시글 읽어오기
        val rePost = boardService.findBoard(post.id)
        // 게시글이 존재하지 않는 경우
        if (rePost == null) {
            return ResponseEntity.badRequest().body(Response.error<Unit>("게시글이 존재하지 않습니다."))
        }
        // 제목 및 내용 빈칸 확인
        if (rePost.isComplete == 1) {
            return ResponseEntity.badRequest().body(Response.error<Unit>("채택된 글은 수정이 불가합니다."))
        }
        if (post.title.isEmpty() || post.body.isEmpty()) {
            return ResponseEntity.badRequest().body(Response.error<Unit>("제목과 내용을 입력해주세요."))
        }
        // 본인 게시글 확인
        if (rePost.userId != auth.getUserData().id) {
            return ResponseEntity.badRequest().body(Response.error<Unit>("권한이 없습니다."))
        }

        // 수정
        // boardService.updatePost(post)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/search")
    fun searchPost(@RequestBody post: SetPostDTO): ResponseEntity<ResponseUnit> {
        // 검색어가 포함된 게시글 읽어오기
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/category")
    fun readCategory(@RequestBody post: SetPostDTO): ResponseEntity<ResponseUnit> {
        // 장르별 게시글 읽어오기
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/my")
    fun readMyPost(@RequestBody post: SetPostDTO): ResponseEntity<ResponseUnit> {
        // 내가 쓴 게시글 읽어오기

        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/report")
    fun reportPost(@RequestBody post: SetPostDTO): ResponseEntity<ResponseUnit> {
        // 신고하기
        // boardService.reportPost(post.postId)
        // return: json
        return ResponseEntity.ok(Response.stateOnly(true))
    }

}
