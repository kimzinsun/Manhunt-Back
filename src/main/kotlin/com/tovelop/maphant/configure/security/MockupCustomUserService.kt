package com.tovelop.maphant.configure
import com.tovelop.maphant.dto.UserDTO
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.time.LocalDate
@Configuration
class MockupCustomUserService : UserDetailsService {
    override fun loadUserByUsername(username: String?): MockupCustomUser {
        if (username == "mockup@test.com") return MockupCustomUser(
            "mockup@test.com", "mockup", UserDTO(
                id = 1,
                email = "test@ks.ac.kr",
                password = "Password123!",
                nickname = "NickName",
                name = "User Name",
                phNum = "1234567890",
                sno = "2017648070",
                createdAt = LocalDate.now(),
                role = "user",
                state = 1,
                agreedAt = LocalDate.now(),
                lastmodifiedAt = LocalDate.now(),
                univId = 123
            )
        )
        else throw UsernameNotFoundException("User not found")
    }
}