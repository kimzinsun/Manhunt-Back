package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.dto.CommentRequest
import com.tovelop.maphant.dto.ReportComment
import com.tovelop.maphant.service.CommentService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comment")
class CommentController(@Autowired val commentService: CommentService) {

    @GetMapping("/list/{boardId}")
    fun findAllComment(@PathVariable boardId: Int): ResponseEntity<Response<List<CommentDTO>>> {
        val comment = commentService.findAllComment(boardId)
        return ResponseEntity.ok().body(Response.success(comment))
    }

    @PostMapping("/insert")
    fun insertComment(@RequestBody commentDTO: CommentDTO): ResponseEntity<ResponseUnit> {
        if (commentDTO.body.isBlank()) {
            return ResponseEntity.badRequest().body(Response.error("댓글 내용을 입력해주세요."))
        }
        commentService.insertComment(commentDTO)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @DeleteMapping("/delete")
    fun deleteComment(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        commentService.deleteComment(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/update")
    fun updateComment(@RequestBody commentDTO: CommentDTO): ResponseEntity<ResponseUnit> {
        commentService.updateComment(commentDTO)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/insert-like")
    fun insertCommentLike(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        commentService.insertCommentLike(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/find-like")
    fun findCommentLike(@RequestBody commentRequest: CommentRequest): ResponseEntity<Response<Int>> {
        commentService.findCommentLike(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.success(commentRequest.commentId))
    }


    @GetMapping("/cnt-like/{commentId}")
    fun cntCommentLike(@PathVariable commentId: Int): ResponseEntity<ResponseUnit> {
        commentService.cntCommentLike(commentId)
        println(commentService.cntCommentLike(commentId))
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/delete-like")
    fun deleteCommentLike(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        commentService.deleteCommentLike(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/report")
    fun insertCommentReport(@RequestBody reportComment: ReportComment): ResponseEntity<ResponseUnit> {
        commentService.insertCommentReport(reportComment.userId, reportComment.commentId, reportComment.reportReason)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/find-report")
    fun findCommentReport(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        commentService.findCommentReport(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

}