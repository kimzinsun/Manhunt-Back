package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.FcmMessageDTO
import com.tovelop.maphant.dto.NotificationResponseDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface NotificationMapper {
    fun getNotificationsByUserId(userId: Int): List<NotificationResponseDTO>
    fun createNotification(notification: FcmMessageDTO, jsonStr: String)
}