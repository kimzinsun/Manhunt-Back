package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.CreateRoomDto
import com.tovelop.maphant.dto.RoomDto
import com.tovelop.maphant.dto.RoomListResultDto
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface RoomMapper {
    fun findRoom(sender_id: Int, receiver_id: Int): RoomDto
    fun createRoom(createHeaderDto: CreateRoomDto): Boolean
    fun updateSenderUnreadCountAndLastContent(room_id: Int, content: String): Boolean
    fun updateReceiverUnreadCountAndLastContent(room_id: Int, content: String): Boolean

    fun findRoomById(room_id: Int): RoomDto
    fun findRoomList(user_id: Int): List<RoomListResultDto>
    fun updateSenderIsDeletedAndSenderUnreadCountZero(room_id: Int): Boolean
    fun updateReceiverIsDeletedAndReceiverUnreadCountZero(room_id: Int): Boolean

    fun findUnReadDmCount(user_id: Int): Int
}