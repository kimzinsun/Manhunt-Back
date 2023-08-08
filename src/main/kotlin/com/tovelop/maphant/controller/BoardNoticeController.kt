package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.BoardNoticeDTO
import com.tovelop.maphant.service.BoardNoticeService
import com.tovelop.maphant.type.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/notice")
class BoardNoticeController(@Autowired val boardNoticeService: BoardNoticeService) {

    @GetMapping("/{id}")
    fun getNotice(@PathVariable("id") noticeId: Int): ResponseEntity<Any> {
        val notice =
            boardNoticeService.findNotice(noticeId) ?: return ResponseEntity.badRequest().body("존재하지 않거나 삭제된 공지입니다.")
        return ResponseEntity.ok().body(notice)
    }

    @PostMapping("/create")
    fun insertNotice(@RequestBody boardNoticeDTO: BoardNoticeDTO): ResponseEntity<Any> {
        boardNoticeService.insertNotice(boardNoticeDTO)
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }
}