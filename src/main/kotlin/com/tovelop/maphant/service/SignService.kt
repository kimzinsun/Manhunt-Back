package com.tovelop.maphant.service

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class SignService(private val mapper: UserMapper) {
    fun signUp(user: UserDTO): Boolean {
        // 회원 가입 로직
        if (!isEmailValid(user.email)) {
            return false
        }
        if (!isPasswordValid(user.password)) {
            return false
        }
        if (!isNicknameValid(user.nickname)) {
            return false
        }
        if (!isUniversityValid(user.university_id)) {
            return false
        }
        if (duplicateEmail(user.email)) {
            return false
        }
        if (duplicateNickname(user.nickname)) {
            return false
        }

        insertUser(user)
        return true
    }

    fun login(email: String, password: String): String? {
        // 로그인 로직
        val user = getUser(email)
        if (user != null && user.password == password) {
            return user.id.toString()
        }
        return null
    }

    fun getUser(email: String): UserDTO? {
        // 사용자 조회 로직
        return mapper.readAllColumnVal(listOf(email)).firstOrNull()
    }

    fun insertUser(user: UserDTO) {
        // 사용자 추가 로직
        mapper.insertUser(user)
    }

    fun isPasswordValid(password: String): Boolean {
        // 비밀번호 유효성 검사 로직
        // ... 구현 내용
        return true
    }

    fun isEmailValid(email: String): Boolean {
        // 이메일 유효성 검사 로직
        // ... 구현 내용
        return true
    }

    fun isNicknameValid(nickname: String): Boolean {
        // 닉네임 유효성 검사 로직
        // ... 구현 내용
        return true
    }

    fun isUniversityValid(universityId: Int?): Boolean {
        // 대학교 유효성 검사 로직
        // ... 구현 내용
        return true
    }

    fun duplicateEmail(email: String): Boolean {
        // 이메일 중복 검사 로직
        // ... 구현 내용
        return false
    }

    fun duplicateNickname(nickname: String): Boolean {
        // 닉네임 중복 검사 로직
        // ... 구현 내용
        return false
    }
}
