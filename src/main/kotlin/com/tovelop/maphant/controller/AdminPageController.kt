package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.AdminBoardReportDTO
import com.tovelop.maphant.dto.AdminCommentReportDTO
import com.tovelop.maphant.dto.BoardReportDTO
import com.tovelop.maphant.dto.BoardReportInfoDTO
import com.tovelop.maphant.service.AdminPageService
import com.tovelop.maphant.service.BoardService
import com.tovelop.maphant.service.UserService
import com.tovelop.maphant.type.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminPageController(@Autowired val adminPageService: AdminPageService) {
    @GetMapping("/reportlist/board")
    fun listBoardReport(@RequestParam sortType: String): ResponseEntity<Response<List<AdminBoardReportDTO>>> {
        val findBoardReport = adminPageService.findBoardReport(sortType, 10)
        return if (findBoardReport == null) ResponseEntity.badRequest().body(Response.error("유효하지 않은 정렬 타입입니다."))
            else ResponseEntity.ok().body(Response.success(findBoardReport))
    }
    @GetMapping("/reportinfo/board")
    fun boardReportInfo(@RequestParam boardId: Int): ResponseEntity<Response<List<BoardReportInfoDTO>>> {
        val findBoardReportInfo = adminPageService.findBoardReportInfo(boardId)
        return ResponseEntity.ok().body(Response.success(findBoardReportInfo))
    }
    @GetMapping("/reportlist/comment")
    fun listCommentReport(@RequestParam sortType: String): ResponseEntity<Response<List<AdminCommentReportDTO>>> {
        val findCommentReport = adminPageService.findCommentReport(sortType, 10)
        return if (findCommentReport == null) ResponseEntity.badRequest().body(Response.error("유효하지 않은 정렬 타입입니다."))
        else ResponseEntity.ok().body(Response.success(findCommentReport))
    }


}
