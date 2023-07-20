package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.service.CommentService
import com.tovelop.maphant.type.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/comment")
class CommentController(@Autowired val commentService: CommentService) {

    @GetMapping("/list")
    fun findAllComment(boardId: Int): ResponseEntity<Response<Int>> {
        commentService.findAllComment(boardId)
        return ResponseEntity.ok().body(Response.success(boardId))
    }

    @PostMapping("/insert")
    fun insertComment(commentDTO: CommentDTO): ResponseEntity<Response<CommentDTO>> {
        return if (commentDTO.body.isBlank()) {
            ResponseEntity.badRequest().body(Response.error("빈칸을 확인해주세요"))
        } else ResponseEntity.ok().body(Response.success(commentDTO))
        // TODO : 추가적인 유효성 검사?
    }

    @PostMapping("/delete")
    fun deleteComment(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        val comment = commentService.getCommentById(commentId)
        if (comment == null) {
            return ResponseEntity.badRequest().body(Response.error("존재하지 않는 댓글입니다."))
        }
        if (comment.userId != userId) {
            return ResponseEntity.badRequest().body(Response.error("자신의 댓글만 삭제할 수 있습니다."))
        }
        commentService.deleteComment(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

    @PostMapping("/update")
    fun updateComment(commentDTO: CommentDTO): ResponseEntity<Response<CommentDTO>> {
        commentService.updateComment(commentDTO)
        return ResponseEntity.ok().body(Response.success(commentDTO))
        // TODO : 자기 글인지 확인
    }

    @GetMapping("/count")
    fun countComment(boardId: Int): ResponseEntity<Response<Int>> {
        commentService.cntComment(boardId)
        return ResponseEntity.ok().body(Response.success(boardId))
    }

    @PostMapping("/insert-like")
    fun insertCommentLike(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        commentService.insertCommentLike(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

    @PostMapping("/find-like")
    fun findCommentLike(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        commentService.findCommentLike(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

    @GetMapping("/cnt-like")
    fun cntCommentLike(commentId: Int): ResponseEntity<Response<Int>> {
        commentService.cntCommentLike(commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

    @PostMapping("/delete-like")
    fun deleteCommentLike(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        commentService.deleteCommentLike(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

    @PostMapping("/report")
    fun insertCommentReport(userId: Int, commentId: Int, reportReason: String): ResponseEntity<Response<Int>> {
        commentService.insertCommentReport(userId, commentId, reportReason)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

    @PostMapping("/find-report")
    fun findCommentReport(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        commentService.findCommentReport(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

}