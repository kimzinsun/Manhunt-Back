package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.BookmarkDTO
import com.tovelop.maphant.service.BookmarkService
import com.tovelop.maphant.type.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/bookmark")
class BookmarkController(val bookmarkService: BookmarkService) {

    //북마크 추가 api
    @PostMapping("/")
    fun bookmark(@RequestBody bordId: BookmarkDTO): ResponseEntity<Any> {
        val bookmarkResult = bookmarkService.insert(bordId)
        if (!bookmarkResult) {
            return ResponseEntity.badRequest().body(
                Response.error<Any>
                    ("이미 등록된 북마크거나 게시물이 삭제되었습니다.")
            )
        }
        return ResponseEntity.ok().body(Response.stateOnly(true))
    }

    //나의 북마크 보기
    @GetMapping("/myBookmarks")
    fun showBookmarks(@RequestBody userId: BookmarkDTO): ResponseEntity<Any> {
        //user_id로 select
        val bookmarkList = bookmarkService.showBookmarks(userId)
        if (!bookmarkList) {
            return ResponseEntity.badRequest().body(Response.error<Any>("요청에 실패했습니다."))
        }

        return ResponseEntity.ok().body(Response.stateOnly(true))
    }
}