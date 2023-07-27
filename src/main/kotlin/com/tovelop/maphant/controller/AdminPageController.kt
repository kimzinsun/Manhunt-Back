package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.service.AdminPageService
import com.tovelop.maphant.service.BoardService
import com.tovelop.maphant.service.UserService
import com.tovelop.maphant.type.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminPageController(@Autowired val adminPageService: AdminPageService) {
    @PostMapping("/")
    fun isAdmin(): ResponseEntity<Any> {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth == null || auth !is TokenAuthToken || !auth.isAuthenticated) {
            return ResponseEntity.badRequest().body(Response.error<Any>("로그인 안됨"))
        }
        val role = auth.getUserData().role
        if (role != "admin") {
            return ResponseEntity.badRequest().body(Response.error<Any>("권한이 없습니다."))
        }
        return ResponseEntity.ok(Response.stateOnly(true))
    }

}