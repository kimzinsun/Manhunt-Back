package com.tovelop.maphant.utils

import com.sendgrid.*
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import com.tovelop.maphant.storage.RedisMockup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class SendGrid (@Autowired val redisMockup: RedisMockup) {
    @Value("\${SEND_GRID_API}") val apiKey: String = ""

    fun sendConfirmMail(email: String) {
        val token = saveEmailToken(email)
        sendMail(email, token)
    }

    fun sendMail(email: String, token: String) {
        val from = Email("admin@ssda.dawoony.com", "SSDA 관리자")
        val subject = "mail test"
        val to = Email(email)
        val content = Content("text/plain", "code : $token")
        val mail = Mail(from, subject, to, content)
        val sg = SendGrid(apiKey)
        val request = Request()
        try {
            request.method = Method.POST
            request.endpoint = "mail/send"
            request.body = mail.build()

            val response: Response = sg.api(request)
            println(response.statusCode)
            println(response.body)
            println(response.headers)
        } catch (e: Exception) {
            throw e
        }
    }

    fun saveEmailToken(email: String): String {
        val random = (0..999999).random().toString().padStart(4, '0')
        return when (redisMockup.setnx(email, random)) {
            0 -> {
                // TODO: 이미 인증 토큰이 존재하는 경우
                println("토큰이 존재합니다")
                redisMockup.get(email).toString()
            }
            1 -> {
                // TODO: 저장에 성공 한 경우
                println("저장 완료")
                random
            }
            else -> {
                // TODO: 나머지
                println("저장 실패")
                throw Error("Failed save token")
            }
        }
    }

    fun confirmEmailToken(email : String, token : String): Boolean {
        val org_token = redisMockup.get(email)
        return if (org_token == token) true
        else false
    }

//    fun createEmail() : String {
//        val from = Email("kwlstjs00@gmail.com")
//        val subject = "mail test"
//        val body = "mail test"
//
//        val email = "To : $from\nSubject: $subject\n\n$body"
//        return email
//    }

}

