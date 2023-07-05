package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.UserMapper
import org.springframework.stereotype.Service


@Service
class UserService(val mapper: UserMapper) {
    fun existIn(value:Any,columnName:String):Boolean{
        return mapper.existIn(value, columnName).isNotEmpty()
    }
}