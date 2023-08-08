package com.tovelop.maphant.service

import com.tovelop.maphant.dto.TagDTO
import com.tovelop.maphant.dto.TagInsertDto
import com.tovelop.maphant.mapper.TagMapper
import org.springframework.stereotype.Service

@Service
class TagService(private val tagMapper: TagMapper) {

    fun getTagList() = tagMapper.getTagList()

    fun insertTag(tagInsertDto: TagInsertDto) {
        tagInsertDto.tagNames.forEach {
            tagMapper.insertTag(TagDTO(0, it, tagInsertDto.categoryId, 1), tagInsertDto.boardId)
        }
    }

    fun getTagByName(name: String) = tagMapper.getTagByName(name)

    fun deleteTagCnt(id: Int, cnt: Int) = tagMapper.deleteTagCnt(id, cnt)
}