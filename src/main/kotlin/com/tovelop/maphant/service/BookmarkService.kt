package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BookmarkDTO
import com.tovelop.maphant.mapper.BookmarkMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookmarkService(val bookmarkMapper: BookmarkMapper) {

    fun insert(boardId: BookmarkDTO): Boolean {
        try {
            bookmarkMapper.insertBoard(boardId)
        } catch (e: Exception) {
            //이미 추가됐거나, 북마크 전에 삭제된 경우
            return false
        }
        return true
    }

    fun showBookmarks(userId: BookmarkDTO): Boolean {
        try {
            bookmarkMapper.selectBoardAllById(userId)
        } catch (e: Exception) {
            // 요청 실패?
            return false
        }
        return true
    }
}