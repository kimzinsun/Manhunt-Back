package com.tovelop.maphant.service

import com.tovelop.maphant.dto.PollDTO
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

    fun createPoll(poll: PollDTO) {
//        println("${poll.boardId}, ${poll.title}, ${poll.expireDateTime}")
//        val result = pollMapper.insertPoll(poll.id, poll.boardId, poll.title, poll.expireDateTime)
        pollMapper.insertPoll(poll)
        poll.options.forEach { pollMapper.insertPollOption(poll.id!!, it) }
    }
}