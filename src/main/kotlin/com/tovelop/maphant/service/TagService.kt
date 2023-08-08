package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.TagMapper
import org.springframework.stereotype.Service

@Service
class TagService(private val tagMapper: TagMapper) {

    fun getTagList() = tagMapper.getTagList()

    fun insertTag(categoryId: Int, boardId: Int, tagNames: List<String>) {
        tagNames.forEach {
            tagMapper.insertTag(categoryId, boardId, it.replace("[^\\p{L}\\p{N}_]".toRegex(), ""))
        }
    }

    fun getTagByName(name: String) = tagMapper.getTagByName(name)

    fun deleteTagCnt(id: Int, cnt: Int) = tagMapper.deleteTagCnt(id, cnt)
}