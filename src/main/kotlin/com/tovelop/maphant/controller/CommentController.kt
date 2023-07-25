package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.service.CommentService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class commentRequest(
    val userId: Int,
    val commentId: Int,
)

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
    fun deleteComment(@RequestBody commentRequest: commentRequest): ResponseEntity<ResponseUnit> {
//        val comment = commentService.getCommentById(commentId)
//        if (comment.user_id != userId) {
//            return ResponseEntity.badRequest().body(Response.error("자신의 댓글만 삭제할 수 있습니다."))
//        }
        commentService.deleteComment(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/update")
    fun updateComment(@RequestBody commentDTO: CommentDTO): ResponseEntity<ResponseUnit> {
//        val comment = commentService.getCommentById(commentDTO.id)
//        if (comment.user_id != userId) {
//            return ResponseEntity.badRequest().body(Response.error("자신의 댓글만 수정할 수 있습니다."))
//        }
        commentService.updateComment(commentDTO)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/insert-like")
    fun insertCommentLike(@RequestBody commentRequest: commentRequest): ResponseEntity<ResponseUnit> {
//        if (commentService.findCommentLike(commentRequest.userId, commentRequest.commentId) != 0) {
//            return ResponseEntity.badRequest().body(Response.error("이미 좋아요를 누른 댓글입니다."))
//        }
        commentService.insertCommentLike(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/find-like")
    fun findCommentLike(@RequestBody commentRequest: commentRequest): ResponseEntity<Response<Int>> {
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
    fun deleteCommentLike(@RequestBody commentRequest: commentRequest): ResponseEntity<ResponseUnit> {
//        val comment = commentService.getCommentById(commentRequest.commentId)
//        if (comment.user_id != commentRequest.userId) {
//            return ResponseEntity.badRequest().body(Response.error("자신의 댓글만 취소할 수 있습니다."))
//        }
        commentService.deleteCommentLike(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/report")
    fun insertCommentReport(userId: Int, commentId: Int, reportReason: String): ResponseEntity<ResponseUnit> {
//        if (commentService.findCommentReport(userId, commentId) != 0) {
//            return ResponseEntity.badRequest().body(Response.error("이미 신고한 댓글입니다."))
//        }
        commentService.insertCommentReport(userId, commentId, reportReason)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/find-report")
    fun findCommentReport(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        commentService.findCommentReport(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

}