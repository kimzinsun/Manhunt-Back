package com.tovelop.maphant.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.tovelop.maphant.dto.FcmMessageDTO
import org.springframework.stereotype.Service

@Service
class FcmService {

    fun send(fcmMessageDTO: FcmMessageDTO) {
        val message = Message.builder()
            .putAllData(fcmMessageDTO.data)
            .setNotification(
                com.google.firebase.messaging.Notification.builder()
                    .setTitle(fcmMessageDTO.notification.title)
                    .setBody(fcmMessageDTO.notification.body)
                    .build()
            )
            .setToken(fcmMessageDTO.to)
            .build()

        val response = FirebaseMessaging.getInstance().send(message)
        println("Successfully sent message: $response")
    }
}
