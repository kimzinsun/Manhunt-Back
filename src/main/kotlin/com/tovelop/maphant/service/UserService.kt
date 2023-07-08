package com.tovelop.maphant.service

import com.tovelop.maphant.dto.UserDTO
import com.tovelop.maphant.mapper.UserMapper
import com.tovelop.maphant.utils.ValidationHelper
import org.springframework.stereotype.Service


@Service
class UserService(val mapper: UserMapper) {
    fun signUp(user: UserDTO): Boolean {
        // 회원 가입 로직
        if (!isEmailValid(user.email)) {
            return false
        }
        // 이미 bcrypt등으로 해싱 되어 있어서 검증 불가.
        // 검증 하겠다고 한다면, bcrypt등이 적용 되어있는지 확인하는 코드가 필요
        // if (!isPasswordValid(user.password)) {
        //     return false
        // }
        if (!isNicknameValid(user.nickname)) {
            return false
        }
        if (!isUniversityValid(user.university_id)) {
            return false
        }
        if (isDuplicateEmail(user.email)) {
            return false
        }
        if (isDuplicateNickname(user.nickname)) {
            return false
        }
        if (isDuplicatePhoneNum(user.phoneInt)) {
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
        return ValidationHelper.isValidPassword(password)
    }

    fun isEmailValid(email: String): Boolean {
        return ValidationHelper.isUniversityEmail(email)
    }

    fun isNicknameValid(nickname: String): Boolean {
        return ValidationHelper.isAlphaNumericKorean(nickname)
    }

    fun isUniversityValid(universityId: Int?): Boolean {
        if (universityId == null) {
            return true
        }

        return mapper.isUniversityExist(universityId)
    }

    fun isDuplicateEmail(email: String): Boolean {
        return mapper.countSameEmails(email) > 0
    }

    fun isDuplicateNickname(nickname: String): Boolean {
        return mapper.countSameNickName(nickname) > 0
    }
    fun isDuplicatePhoneNum(phoneNum: String): Boolean {
        return mapper.countSamePhoneInt(phoneNum) > 0
    }

}