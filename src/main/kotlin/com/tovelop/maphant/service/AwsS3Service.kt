package com.tovelop.maphant.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import com.tovelop.maphant.dto.UploadLogDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.time.LocalDate
import java.util.*


@Service
class AwsS3Service(@Autowired val amazonS3Client: AmazonS3Client) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket:String

    //파일을 aws 보내고 url 받기
    fun uploadFiles(files:List<MultipartFile>): List<String> {
        var imageUrls = ArrayList<String>()
        for (file in files) {
            val originalFileName: String? = file.originalFilename
            //uuid로 임의의 파일네임 생성 //일단 추가해놓음
            //val uploadFileName:String? = getUuidFileName(originalFileName.toString())

            val objectMetadata = ObjectMetadata()
            objectMetadata.setContentLength(file.getSize())
            objectMetadata.setContentType(file.getContentType())

            try {
                val inputStream:InputStream = file.inputStream
                amazonS3Client.putObject(bucket,originalFileName, inputStream,objectMetadata);
                val uploadFileUrl = amazonS3Client.getUrl(bucket, originalFileName).toString();
                imageUrls.add(uploadFileUrl)
            }catch (e: IOException) {
                e.printStackTrace();
            }
        }
        return imageUrls
    }

    // url db 저장
    @Transactional
    fun storeUrl(files: List<MultipartFile>): ArrayList<UploadLogDTO> {
        val dtoList = ArrayList<UploadLogDTO>()
        val imageUrls: List<String> = uploadFiles(files)

        for ((index, file) in files.withIndex()) {
            val storeUrlDto = UploadLogDTO(
                number = 0,
                user_id = 1,
                file_size = file.size,
                upload_date = LocalDate.now(),
                url = imageUrls[index]
            )
            dtoList.add(storeUrlDto)
        }
        return dtoList
    }
//
//    fun getUuidFileName(fileName: String): String? {
//        val ext = fileName.substring(fileName.indexOf(".") + 1)
//        return UUID.randomUUID().toString() + "." + ext
//    }

}