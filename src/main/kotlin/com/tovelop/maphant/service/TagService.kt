package com.tovelop.maphant.service

import com.tovelop.maphant.dto.TagDTO
import com.tovelop.maphant.mapper.TagMapper

class TagService(private val tagMapper: TagMapper) {

    fun getTagList() = tagMapper.getTagList()

    fun insertTag(tagDTO: TagDTO) = tagMapper.insertTag(tagDTO)

    fun getTagByName(name: String) = tagMapper.getTagByName(name)

    fun deleteTagCnt(id: Int, cnt: Int) = tagMapper.deleteTagCnt(id, cnt)
}