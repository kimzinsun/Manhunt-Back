package com.tovelop.maphant.service

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.UserMapper
import com.tovelop.maphant.utils.VaildationHelper
import org.springframework.stereotype.Service


@Service
class UserService(val mapper: UserMapper) {
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

    fun isEmailExist(email: String): Boolean {
        return mapper.countSameEmails(email) != 0
    }

    fun isNicknameExist(nickName: String): Boolean {
        return mapper.countSameNickName(nickName) != 0
    }

    fun existSamePhoneInt(phoneInt: String): Boolean {
        return mapper.countSamePhoneInt(phoneInt) != 0
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
        return VaildationHelper.isPasswordValid(password)
    }

    fun isEmailValid(email: String): Boolean {
        return VaildationHelper.isEmailValid(email)
    }

    fun isNicknameValid(nickname: String): Boolean {
        return VaildationHelper.isAlphaNumericKorean(nickname)
    }

    fun isUniversityValid(universityId: Int?): Boolean {
        if (universityId == null) {
            return true
        }

        return mapper.isUniversityExist(universityId)
    }

    fun duplicateEmail(email: String): Boolean {
        return mapper.countSameEmails(email) > 0
    }

    fun duplicateNickname(nickname: String): Boolean {
        return mapper.countSameNickName(nickname) > 0
    }

}