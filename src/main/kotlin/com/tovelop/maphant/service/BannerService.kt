package com.tovelop.maphant.service

import com.tovelop.maphant.dto.BannerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BannerService(@Autowired val bannerService: BannerService) {
    fun insertBanner(bannerDTO: BannerDTO) {
        bannerService.insertBanner(bannerDTO)
    }
    fun deleteBanner(bannerId: Int) {
        bannerService.deleteBanner(bannerId)
    }
    fun findBannerByBannerId(bannerId: Int) {

    }
    fun findBannerByCompany(company: String) {

    }

}