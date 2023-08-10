package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.ReqTagDTO
import com.tovelop.maphant.dto.TagDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface TagMapper {
    fun insertTag(categoryId: Int, boardId: Int, tagName: String)
    fun deleteTag(boardId: Int, tagId: Int):Boolean

    //게시글 수정 시 해당 함수의 반환값이 0일때만 태그 추가 가능
    fun getTagExistenceInBoard(boardId: Int, tagId: Int):Int
    fun insertBoardTag(boardId: Int, tagId: Int)
    fun getTagByName(name: String): TagDTO?
    fun getTagList(): List<TagDTO>
    fun deleteTagCnt(id: Int, cnt: Int)
    fun findBoardTags(boardId: Int): List<ReqTagDTO>
}