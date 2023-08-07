package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.TagDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface TagMapper {
    fun getTagList(): List<TagDTO>
    fun insertTag(tag: TagDTO)
    fun getTagByName(name: String)
    fun updateTag(id: Int, name: String)
    fun updateTagCnt(id: Int, cnt: Int)
    fun deleteTagCnt(id: Int, cnt: Int)
}