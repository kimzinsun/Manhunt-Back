package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.TagMapper
import org.springframework.stereotype.Service

@Service
class TagService(private val tagMapper: TagMapper) {
    fun insertTag(categoryId: Int, boardId: Int, tagNames: List<String>) {
        tagNames.forEach {
            tagMapper.insertTag(categoryId, boardId, it.replace("[^\\p{L}\\p{N}_]".toRegex(), ""))
        }
    }

    fun deleteTag(boardId: Int, tagId: Int):Boolean = tagMapper.deleteTag(boardId, tagId)

    fun modifyTag(categoryId: Int, boardId: Int, tagId: Int, tagName: String){
        if(tagMapper.getTagExistenceInBoard(boardId, tagId)==0){
            tagMapper.insertTag(categoryId, boardId, tagName.replace("[^\\p{L}\\p{N}_]".toRegex(), ""))
        }
    }

    fun insertBoardTag(boardId: Int, tagId: Int) = tagMapper.insertBoardTag(boardId, tagId)

    fun getTagByName(name: String) = tagMapper.getTagByName(name.replace("[^\\p{L}\\p{N}_]".toRegex(), ""))

    fun getTagList() = tagMapper.getTagList()
    
    fun deleteTagCnt(id: Int, cnt: Int) = tagMapper.deleteTagCnt(id, cnt)
}