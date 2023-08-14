package com.tovelop.maphant.controller

import com.amazonaws.services.ec2.model.transform.DiskImageStaxUnmarshaller
import org.springframework.http.ResponseEntity
import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.*
import com.tovelop.maphant.mapper.UserMapper
import com.tovelop.maphant.configure.security.PasswordEncoderSHA512

import com.tovelop.maphant.service.AdminPageService
import com.tovelop.maphant.service.BannerService
import com.tovelop.maphant.service.BoardService
import com.tovelop.maphant.service.UserService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import jdk.jfr.consumer.RecordedThread
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Controller
@RequestMapping("/admin")
class AdminPageController(
    @Autowired val adminPageService: AdminPageService,
    @Autowired val bannerService: BannerService
) {
    @GetMapping("/")
    fun listBoardReport(model: Model, @RequestParam sortType: String?): String {
        val findBoardReport = adminPageService.findBoardReport(sortType ?: "reportedAt", 5)
        val findCommentReport = adminPageService.findCommentReport(sortType ?: "reportedAt", 5)
        val findUserList = adminPageService.findAllUserSanction()
        model.addAttribute("boardReport", findBoardReport)
        model.addAttribute("commentReport", findCommentReport)
        model.addAttribute("userSanction", findUserList)
        return "admin_index_page"
    }

    @GetMapping("/reportInfo/board")
    fun boardReportInfo(@RequestParam boardId: Int): ResponseEntity<Response<List<BoardReportInfoDTO>>> {
        val findBoardReportInfo = adminPageService.findBoardReportInfo(boardId)
        return ResponseEntity.ok().body(Response.success(findBoardReportInfo))
    }

    @GetMapping("/reportInfo/comment")
    fun commentReportInfo(@RequestParam commentId: Int): ResponseEntity<Response<List<CommentReportInfoDTO>>> {
        val findBoardReportInfo = adminPageService.findCommentReportInfo(commentId)
        return ResponseEntity.ok().body(Response.success(findBoardReportInfo))
    }

    @PostMapping("/sanction/board")
    fun sanctionBoard(@RequestParam boardId: Int): ResponseEntity<ResponseUnit> {
        adminPageService.updateBoardSanction(boardId)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/sanction/comment")
    fun sanctionComment(@RequestParam commentId: Int): ResponseEntity<ResponseUnit> {
        adminPageService.updateCommentSanction(commentId)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/sanction/user")
    fun sanctionUser(@RequestBody userReportDTO: UserReportDTO): ResponseEntity<ResponseUnit> {
        if (adminPageService.findReportByUserId(userReportDTO.userId)) {
            return ResponseEntity.ok(Response.stateOnly(false))
        }
        //유저를 정지 상태(2)로 변경
        //정지할 유저가 작성한 모든 글, 댓글을 임시 블락 상태(3)으로 변경
        adminPageService.updateBoardBlockByUserId(userReportDTO.userId)
        adminPageService.updateCommentBlockByUserId(userReportDTO.userId)
        //유저 제재 내역 테이블에 삽입
        adminPageService.insertUserReport(userReportDTO)
        adminPageService.updateUserState(userReportDTO.userId, 2)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/unSanction/user")
    fun unSanctionUser(@RequestParam userId: Int): ResponseEntity<ResponseUnit> {
        //유저 제재 내역 테이블에서 삭제
        adminPageService.deleteRecentUserReportByUserId(userId)
        //유저를 정상 상태(1)로 변경
        adminPageService.updateUserState(userId, 1)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/reportInfo/user")
    fun userReportInfo(@RequestParam userId: Int): ResponseEntity<Response<List<UserReportDTO>>> {
        val findUserReportInfo = adminPageService.findReportInfoByUserId(userId)
        return ResponseEntity.ok().body(Response.success(findUserReportInfo))
    }

    @GetMapping("/login")
    fun loginPage(): String {
        return "admin_login_page"
    }

    @PostMapping("/banner/insert")
    fun insertBanner(@RequestBody bannerDTO: BannerDTO): ResponseEntity<ResponseUnit> {
        bannerService.insertBanner(bannerDTO)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/banner/find/bannerid")
    fun findBannerByBannerId(@RequestParam bannerId: Int): ResponseEntity<Response<BannerDTO>> {
        val findBannerByBannerId = bannerService.findBannerByBannerId(bannerId)
        return ResponseEntity.ok().body(Response.success(findBannerByBannerId))
    }

    @GetMapping("/banner/find/company")
    fun findBannerByCompany(@RequestParam company: String): ResponseEntity<Response<List<BannerDTO>>> {
        val findBannerByCompany = bannerService.findBannerByCompany(company)
        return ResponseEntity.ok().body(Response.success(findBannerByCompany))
    }

    @PostMapping("/banner/update/title")
    fun updateTitleByBannerId(@RequestParam bannerId: Int, @RequestParam title: String): ResponseEntity<ResponseUnit> {
        bannerService.updateTitleByBannerId(bannerId, title)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/banner/update/imagesurl")
    fun updateImagesUrlByBannerId(
        @RequestParam bannerId: Int,
        @RequestParam imagesUrl: String
    ): ResponseEntity<ResponseUnit> {
        bannerService.updateTitleByBannerId(bannerId, imagesUrl)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/banner/update/url")
    fun updateUrlByBannerId(@RequestParam bannerId: Int, @RequestParam url: String): ResponseEntity<ResponseUnit> {
        bannerService.updateTitleByBannerId(bannerId, url)
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @GetMapping("/banner/get")
    fun getBannerByBannerId(@RequestParam bannerId: Int): ResponseEntity<Response<GetBannerDTO>> {
        val getBannerByBannerId = bannerService.getBannerByBannerId(bannerId)
        return ResponseEntity.ok().body(Response.success(getBannerByBannerId))
    }
}
