package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.UserDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface UserMapper {
    fun insertUser(user: UserDTO)
    fun existIn(value:Any,columnName:String):List<UserDTO>
    fun readAllColumnVal(emails: List<String>): List<UserDTO>
    fun updateUserByEmail(id: Int)
}