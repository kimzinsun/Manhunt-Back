package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.MessageDto
import com.tovelop.maphant.dto.SmsResponseDto
import com.tovelop.maphant.service.SmsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SmsController(private val smsService: SmsService) {
    @PostMapping("/sms/send")
    fun sendSms(@RequestBody messageDto: MessageDto): SmsResponseDto {
        return smsService.sendSms(messageDto) as SmsResponseDto
    }
}