package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.token.TokenAuthToken
import com.tovelop.maphant.dto.BoardDTO
import com.tovelop.maphant.dto.ProfileImageDto
import com.tovelop.maphant.dto.UserDataDTO
import com.tovelop.maphant.service.AwsS3Service
import com.tovelop.maphant.service.ProfileService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/profile")
class ProfileController (
    val profileService: ProfileService,
    private val awsS3Service: AwsS3Service
){

    @GetMapping
    fun getProfileImage(): ResponseEntity<Response<ProfileImageDto>>{
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId:Int = auth.getUserData().id!!

        return ResponseEntity.ok().body(Response.success(profileService.getProfileImage(userId)));
    }

    @PatchMapping
    fun updateProfileImage(@RequestParam file:MultipartFile): ResponseEntity<Response<String>>{
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId: Int = auth.getUserData().id!!

        val imageUrl:String = awsS3Service.uploadFile(file)
        // TODO: userId에 해당하는 profile 튜플이 없는 경우 insert 있는 경우 update
        profileService.updateProfileImage(userId, imageUrl, file)

        return ResponseEntity.ok().body(Response.success(imageUrl));
    }

    @GetMapping("/board")
    fun getPostsList(): ResponseEntity<Response<List<BoardDTO>>>{
        val auth = SecurityContextHolder.getContext().authentication!! as TokenAuthToken
        val userId:Int = auth.getUserData().id!!

        return ResponseEntity.ok().body(Response.success(profileService.getBoardsList(userId)));
    }
}