package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.CommentDTO
import com.tovelop.maphant.dto.CommentRequest
import com.tovelop.maphant.dto.ReportComment
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
        if (commentDTO.body.length > 255) {
            return ResponseEntity.badRequest().body(Response.error("댓글은 255자 이내로 작성해주세요."))
        }
        commentService.insertComment(commentDTO)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @DeleteMapping("/delete")
    fun deleteComment(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        if (commentService.getCommentById(commentRequest.commentId) == null) {
            return ResponseEntity.badRequest().body(Response.error("존재하지 않는 댓글입니다."))
        }
        if (commentService.getCommentById(commentRequest.commentId)!!.user_id != commentRequest.userId) {
            return ResponseEntity.badRequest().body(Response.error("댓글 작성자만 삭제할 수 있습니다."))
        }
        commentService.deleteComment(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/update")
    fun updateComment(@RequestBody commentDTO: CommentDTO): ResponseEntity<ResponseUnit> {
        if (commentService.getCommentById(commentDTO.id) == null) {
            return ResponseEntity.badRequest().body(Response.error("존재하지 않는 댓글입니다."))
        }
        if (commentService.getCommentById(commentDTO.id)!!.user_id != commentDTO.user_id) {
            return ResponseEntity.badRequest().body(Response.error("댓글 작성자만 수정할 수 있습니다."))
        }
        if (commentDTO.body.isBlank()) {
            return ResponseEntity.badRequest().body(Response.error("댓글 내용을 입력해주세요."))
        }
        if (commentDTO.body.length > 255) {
            return ResponseEntity.badRequest().body(Response.error("댓글은 255자 이내로 작성해주세요."))
        }
        if (commentService.getCommentById(commentDTO.id)!!.is_anonymous != commentDTO.is_anonymous) {
            return ResponseEntity.badRequest().body(Response.error("댓글 익명 여부는 수정할 수 없습니다."))
        }
        if (commentService.getCommentById(commentDTO.id)!!.like_cnt != commentDTO.like_cnt) {
            return ResponseEntity.badRequest().body(Response.error("댓글 좋아요 수는 수정할 수 없습니다."))
        }
        if (commentService.getCommentById(commentDTO.id)!!.id != commentDTO.id) {
            return ResponseEntity.badRequest().body(Response.error("댓글 번호는 수정할 수 없습니다."))
        }
        commentService.updateComment(commentDTO)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/insert-like")
    fun insertCommentLike(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        commentService.insertCommentLike(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    @PostMapping("/find-like") // TODO : 수정
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

    @PostMapping("/find-report") // TODO : 수정
    fun findCommentReport(@RequestBody commentRequest: CommentRequest): ResponseEntity<ResponseUnit> {
        commentService.findCommentReport(commentRequest.userId, commentRequest.commentId)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

}