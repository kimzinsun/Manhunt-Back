package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.TagDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface TagMapper {
    fun getTagList(): List<TagDTO>
    fun insertTag(tag: TagDTO, boardId: Int)
    fun getTagByName(name: String)
    fun deleteTagCnt(id: Int, cnt: Int)
}