package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.UploadLogDTO
import com.tovelop.maphant.service.AwsS3Service
import com.tovelop.maphant.service.ProfileService
import com.tovelop.maphant.service.UploadLogService
import com.tovelop.maphant.type.response.SuccessResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/profile")
class ProfileController (
    val profileService: ProfileService,
    private val awsS3Service: AwsS3Service,
    private val uploadLogService: UploadLogService
){

    @GetMapping
    fun getProfileImage(): SuccessResponse<UploadLogDTO>{
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId:Int = auth.getUserData().id!!

        return SuccessResponse(profileService.getProfileImage(userId))
    }

    @PatchMapping
    fun updateProfileImage(@RequestParam file:MultipartFile):UploadLogDTO{
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId: Int = auth.getUserData().id!!

        val imageUrl:String = awsS3Service.uploadFile(file)
        return uploadLogService.storeOneUrl(userId, imageUrl, file)
    }

    @GetMapping("/board")
    fun getPostsList(userInt: Int):SuccessResponse<List<BoardDTO>>{
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId:Int = auth.getUserData().id!!

        return SuccessResponse(profileService.getBoardsList(userId))
    }
}