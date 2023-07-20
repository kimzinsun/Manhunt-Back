package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.PollMapper
import org.springframework.stereotype.Service

@Service
class PollService(val pollMapper: PollMapper) {

    fun increaseOptionCount(userId: Int, pollId: Int, pollOption: String): Boolean {
        try {
            pollMapper.insertPollUser(userId, pollId, pollOption)
        } catch (e: Exception) {
            return false
        }
        return true
    }
}