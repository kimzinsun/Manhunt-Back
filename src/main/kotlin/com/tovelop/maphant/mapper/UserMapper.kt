package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.UserDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Mapper
@Repository
interface UserMapper {
    fun insertUser(user: UserDTO)
    fun countSameEmails(email: String): Int
    fun countSameNickName(nickName: String): Int
    fun countSamePhoneNum(phoneNum: String): Int
    fun readAllColumnVal(emails: List<String>): List<UserDTO>
    fun findEmailBysNo(sNo: String, phoneNum: String): String?
    fun updateUserByEmail(id: Int)
    fun findUniversityIdBy(univName: String): Int?
    fun isUniversityExist(univId: Int): Boolean
    fun findUniversityUrlBy(univId: Int?): String
    fun updateUserState(email: String, state: Char)
    fun updateUserPasswordByEmail(email: String, password: String)
    fun updateUserNicknameByEmail(email: String, password: String)
    fun updateUserPhoneNumByEmail(email: String, password: String)
    fun findPasswordByEmail(email: String): String
    fun findNicknameByEmail(email: String): String
    fun findStateByUserId(userId: Int): Char
    fun getAllCategories(): List<String>
    fun getAllMajors(): List<String>
    fun getAllUnivNames(): List<String>
    fun insertCategoryIdMajorIdByUserId(userId: Int, categoryId: Int, majorId: Int)
    fun findUserIdByUserEmail(email: String): Int
    fun findCategoryIdByCategoryName(categoryName: String): Int
    fun findMajorIdByMajorName(majorName: String): Int
}