package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.UserMapper
import org.springframework.stereotype.Service


@Service
class UserService(val mapper: UserMapper) {
    fun existSameEmail(email: String): Boolean {
        return mapper.countSameEmails(email) != 0
    }

    fun existSameNickName(nickName: String): Boolean {
        return mapper.countSameNickName(nickName) != 0
    }

    fun existSamePhoneInt(phoneInt: String): Boolean {
        return mapper.countSamePhoneInt(phoneInt) != 0
    }

}