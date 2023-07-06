package com.tovelop.maphant.configure

import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class MockupCustomUserService : UserDetailsService {
    override fun loadUserByUsername(username: String?): MockupCustomUser {
        if (username == "mockup@test.com")
            return MockupCustomUser("mockup@test.com", "mockup")
        else
            throw RuntimeException("User not found")
    }
}