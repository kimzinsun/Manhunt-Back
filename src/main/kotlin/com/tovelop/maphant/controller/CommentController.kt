package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.service.CommentService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
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

    @DeleteMapping("/delete/{userId}/{commentId}")
    fun deleteComment(@PathVariable userId: Int, @PathVariable commentId: Int): ResponseEntity<ResponseUnit> {
//        val comment = commentService.getCommentById(commentId)
//        if (comment.user_id != userId) {
//            return ResponseEntity.badRequest().body(Response.error("자신의 댓글만 삭제할 수 있습니다."))
//        }
        // TODO : 자신의 댓글만 삭제 할 수 있도록 수정하기
        commentService.deleteComment(userId, commentId)
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
    fun insertCommentLike(userId: Int, commentId: Int): ResponseEntity<ResponseUnit> {
//        if (commentService.findCommentLike(userId, commentId) != 0) {
//            return ResponseEntity.badRequest().body(Response.error("이미 좋아요를 누른 댓글입니다."))
//        }
        commentService.insertCommentLike(userId, commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
        // TODO : 여기부터 수정하기
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
    fun deleteCommentLike(userId: Int, commentId: Int): ResponseEntity<ResponseUnit> {
//        val comment = commentService.getCommentById(commentId)
//        if (comment.user_id != userId) {
//            return ResponseEntity.badRequest().body(Response.error("자신의 댓글만 취소할 수 있습니다."))
//        }
        commentService.deleteCommentLike(userId, commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/report")
    fun insertCommentReport(userId: Int, commentId: Int, reportReason: String): ResponseEntity<ResponseUnit> {
        if (commentService.findCommentReport(userId, commentId) != 0) {
            return ResponseEntity.badRequest().body(Response.error("이미 신고한 댓글입니다."))
        }
        commentService.insertCommentReport(userId, commentId, reportReason)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/find-report")
    fun findCommentReport(userId: Int, commentId: Int): ResponseEntity<Response<Int>> {
        commentService.findCommentReport(userId, commentId)
        return ResponseEntity.ok().body(Response.success(commentId))
    }

}