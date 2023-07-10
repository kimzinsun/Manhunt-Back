package com.tovelop.maphant.dto

import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate

data class SignupDTO(
    val email: String,
    val password: String,
    val passwordChk: String,
    val nickname: String,
    val name: String,
    val sNo: String,
    val phoneNo: String,
    val universityId: Int?
) {
    fun toUserDTO(passwordEncoder: PasswordEncoder): UserDTO {
        return UserDTO(
            email = email,
            password = passwordEncoder.encode(password),
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
