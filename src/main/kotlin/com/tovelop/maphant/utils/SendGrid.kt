package com.tovelop.maphant.utils

import org.springframework.stereotype.Component

@Component
class SendGrid {
    fun sendMail() {

    }
}

data class MailData(
    val title: String,
    val content: String,
    val to: String,
    val from: String
)