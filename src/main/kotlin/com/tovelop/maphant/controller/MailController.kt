package com.tovelop.maphant.controller

import com.tovelop.maphant.utils.SendGrid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MailController(@Autowired private val sendGrid: SendGrid) {
    @PostMapping("/send-mail")
    fun sendMail(@RequestParam email: String): ResponseEntity<String> {
        return try {
            sendGrid.sendConfirmMail(email)
            ResponseEntity.ok("success")
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail")
        }
    }

    @PostMapping("/confirms")
    fun verification(@RequestBody confirmReq: ConfirmReq): ResponseEntity<Boolean> {
        return if (sendGrid.confirmEmailToken(confirmReq.email, confirmReq.token)) ResponseEntity.ok(true)
        else ResponseEntity.badRequest().body(false)
    }

}

data class ConfirmReq(
    val email: String,
    val token: String
)