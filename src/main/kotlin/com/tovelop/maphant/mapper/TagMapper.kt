package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.ReqTagDTO
import com.tovelop.maphant.dto.TagDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface TagMapper {
    fun insertTag(categoryId: Int, boardId: Int, tagName: String)
    fun insertBoardTag(boardId: Int, tagId: Int)
    fun getTagByName(name: String): TagDTO?
    fun getTagList(): List<TagDTO>
    fun deleteTagCnt(id: Int, cnt: Int)
    fun findBoardTags(boardId: Int): List<ReqTagDTO>
}