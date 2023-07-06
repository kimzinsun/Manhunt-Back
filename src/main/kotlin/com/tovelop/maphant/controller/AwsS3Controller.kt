package com.tovelop.maphant.controller

import com.tovelop.maphant.service.AwsS3Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartFile

@Controller
class AwsS3Controller(@Autowired private val awsS3Service: AwsS3Service) {

    @PostMapping("/upload")
    @ResponseBody
    fun uploadFile(@RequestParam files:List<MultipartFile>):String {

        if(files.size > 5) {
            // 에러 던지기
            throw MaxUploadSizeExceededException(5)
        }
        //시큐리티 붙이기전 userId 1로 가정
        val userId: Int = 1

        // 서비스에서 여러 파일 aws에 올려서 url 받기
        val imageUrls:List<String> = awsS3Service.uploadFiles(files);
        //

        return "ok";
    }
}