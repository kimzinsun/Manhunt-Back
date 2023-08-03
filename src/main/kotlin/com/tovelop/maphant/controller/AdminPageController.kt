package com.tovelop.maphant.controller

import com.tovelop.maphant.service.AdminPageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/admin")
class AdminPageController(@Autowired val adminPageService: AdminPageService) {
    @GetMapping("/reportlist/board")
    @ResponseBody
    fun listBoardReport() /*: ResponseEntity<Response<List<BoardReportDTO>>>*/ {
        // 검색 후 비어있으면 비어있다고 에러처리
        // 아니면 보드리포트DTO 리스트 반환
    }

    @GetMapping("/login")
    fun loginPage(): String {
        return "admin_login_page"
    }
}
