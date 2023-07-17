package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BookmarkDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BookmarkMapper {

    fun insertBoard(userId: String?, boardId: Int)

    fun selectBoardAllById(userId: String?): List<BookmarkDTO>
}