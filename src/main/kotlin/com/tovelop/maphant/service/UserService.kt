package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.UserMapper
import org.springframework.stereotype.Service


@Service
class   UserService(val mapper: UserMapper) {
    fun existSameEmail(email:String):Boolean{
        return mapper.countSameEmails(email) != 0
    }

}