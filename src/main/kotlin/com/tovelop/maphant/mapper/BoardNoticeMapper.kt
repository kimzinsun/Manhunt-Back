package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BoardNoticeDTO
import com.tovelop.maphant.dto.BoardNoticeListDTO
import com.tovelop.maphant.dto.UpdateBoardNoticeDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.data.annotation.Id
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BoardNoticeMapper {
    fun insertNotice(boardNoticeDTO: BoardNoticeDTO)
    fun findBoard(noticeId: Id): BoardNoticeDTO
    fun updateNotice(updateBoardNoticeDTO: UpdateBoardNoticeDTO)
    fun deleteNotice(noticeId: Id)
    fun findNoticeList(): List<BoardNoticeListDTO>

}