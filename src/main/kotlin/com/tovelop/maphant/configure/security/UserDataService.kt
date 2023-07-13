package com.tovelop.maphant.configure.security

import com.tovelop.maphant.dto.UserDTO
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.time.LocalDate

@Configuration
class UserDataService: UserDetailsService {
    override fun loadUserByUsername(username: String?): UserData {
        if(username == "test@test.com") return UserData(
                "test@test.com", "1234", UserDTO(
                id = 1,
                email = "test@test.com",
                password = "1234",
                nickname = "NickName",
                name = "User Name",
                phoneInt = "1234567890",
                sNo = "2017648070",
                create_at = LocalDate.now(),
                role = "user",
                state = "1",
                is_agree = "Yes",
                last_modified_date = LocalDate.now(),
                university_id = 123
            )
        )

        throw UsernameNotFoundException("Not found")
    }
}