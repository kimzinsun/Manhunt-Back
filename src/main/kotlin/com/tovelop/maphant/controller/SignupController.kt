package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(@Autowired private val userMapper: UserMapper) {
    @PostMapping("/register")
    fun registerUser(@RequestBody user: UserDTO) {
        userMapper.insertUser(user)
    }
}