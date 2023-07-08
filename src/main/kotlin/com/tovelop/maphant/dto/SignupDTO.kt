package com.tovelop.maphant.dto

import java.time.LocalDate

data class SignupDTO (
    val email: String,
    val password: String,
    val passwordChk: String,
    val nickname: String,
    val name: String,
    val sNo: String,
    val phoneNo: String,
    val universityId: Int?
) {
    fun toUserDTO(): UserDTO {
        return UserDTO(
            email = email,
            password = password,
            nickname = nickname,
            name = name,
            phoneInt = phoneNo,
            sNo = sNo,
            create_at = LocalDate.now(),
            role = "user",
            state = "0",
            is_agree = "Y",
            last_modified_date = LocalDate.now(),
            university_id = universityId
        )
    }
}
