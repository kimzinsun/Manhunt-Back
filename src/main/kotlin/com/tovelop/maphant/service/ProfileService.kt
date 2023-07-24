package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.ProfileImageDto
import com.tovelop.maphant.dto.UploadLogDTO
import com.tovelop.maphant.mapper.ProfileMapper
import com.tovelop.maphant.type.response.SuccessResponse
import com.tovelop.maphant.utils.UploadUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@Service
class ProfileService (
    private val uploadUtils: UploadUtils,
    var profileMapper: ProfileMapper,
    private val uploadLogService: UploadLogService,
){
    //프로필 이미지의 Dto 불러오기
    fun getProfileImage(userId:Int): ProfileImageDto {
        return profileMapper.findImageById(userId)
    }

    //유저가 작성한 글 목록 불러오기
    fun getBoardsList(userId: Int):List<BoardDTO>{
        return profileMapper.findAllPostsById(userId)
    }

    @Transactional
    fun updateProfileImage(userId: Int, imageUrl: String, file: MultipartFile): String {
        if (uploadUtils.isNotImageFile(file.originalFilename as String))
            throw IllegalArgumentException("png, jpeg, jpg에 해당하는 파일만 업로드할 수 있습니다.");

        uploadLogService.storeOneUrl(userId, imageUrl, file)
        /* TODO Mapper 이용해 db 저장 하기 */
        profileMapper.updateProfileImage(userId, imageUrl)

        return imageUrl;
    }
}