package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.MockupCustomUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class SignInDto(val email : String, val password : String)

@RequestMapping("/user")
@RestController
class TestSignInController(@Autowired private val customUserDetail : MockupCustomUserService) {
    @PostMapping("/signIn")
    fun signIn(@RequestBody signInDto : SignInDto): ResponseEntity<MutableMap<String, String>> {
        return try {
            val mockup = customUserDetail.loadUserByUsername(signInDto.email)
            val res = UsernamePasswordAuthenticationToken(mockup, signInDto.password, mockup.authorities)
            SecurityContextHolder.getContext().authentication = res

            ResponseEntity.ok(mutableMapOf("status" to "success", "data" to SecurityContextHolder.getContext().authentication.name))
        } catch (e : RuntimeException) {
            ResponseEntity.ok(mutableMapOf("status" to "fail", "data" to "User not found"))
        }
    }
}