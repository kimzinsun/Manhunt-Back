package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BannerDTO
import com.tovelop.maphant.mapper.BannerMapper
import okhttp3.internal.format
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
import org.springframework.boot.env.RandomValuePropertySource
import org.springframework.data.annotation.Id
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class BannerService(@Autowired val bannerMapper: BannerMapper) {
    fun insertBanner(bannerDTO: BannerDTO) {
        bannerMapper.insertBanner(bannerDTO)
    }
    fun deleteBanner(bannerId: Int) {
        bannerMapper.deleteBanner(bannerId)
    }
    fun findBannerByBannerId(bannerId: Int): BannerDTO {
        return bannerMapper.findBannerByBannerId(bannerId)
    }
    fun findBannerByCompany(company: String):List<BannerDTO> {
        return bannerMapper.findBannerByCompany(company)
    }
    fun updateTitleByBannerId(bannerId: Int, title: String) {
        bannerMapper.updateTitleByBannerId(bannerId, title)
    }
    fun updateImagesUrlByBannerId(bannerId: Int, imagesUrl: String) {
        bannerMapper.updateImagesUrlByBannerId(bannerId, imagesUrl)
    }
    fun updateUrlByBannerId(bannerId: Int, url: String) {
        bannerMapper.updateUrlByBannerId(bannerId, url)
    }

    /**
     * 광고 노출 빈도수 설정. frequency = 빈도 수
     */
    fun updateFrequency(bannerId: Int, frequency: Int) {
        bannerMapper.updateFrequency(bannerId, frequency)
    }
    fun findPercentageByBannerId(bannerId: Int): Double {
        return bannerMapper.findPayByBannerId(bannerId) / bannerMapper.sumAllPay().toDouble()
    }
    fun findBannerByPercentage(bannerId: Int): Boolean {
        val ramdomValue= Random.nextDouble()
        return ramdomValue <= findPercentageByBannerId(bannerId)
    }
}