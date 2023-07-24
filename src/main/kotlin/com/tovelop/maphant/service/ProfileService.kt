package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.UploadLogDTO
import com.tovelop.maphant.mapper.ProfileMapper
import com.tovelop.maphant.type.response.SuccessResponse
import org.springframework.stereotype.Service

@Service
class ProfileService (
    var profileMapper: ProfileMapper
){
    //프로필 이미지의 Dto 불러오기
    fun getProfileImage(userId:Int): UploadLogDTO {
        return profileMapper.findImageById(userId)
    }

    //유저가 작성한 글 목록 불러오기
    fun getBoardsList(userId: Int):List<BoardDTO>{
        return profileMapper.findAllPostsById(userId)
    }
}