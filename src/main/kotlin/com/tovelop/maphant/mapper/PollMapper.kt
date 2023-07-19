package com.tovelop.maphant.mapper

import com.tovelop.maphant.controller.PollDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface PollMapper {

    fun insertPoll(poll: PollDTO)

}