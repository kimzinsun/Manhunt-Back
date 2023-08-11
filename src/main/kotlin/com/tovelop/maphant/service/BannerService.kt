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
    fun findBannerByBannerId(bannerId: Int): BannerDTO {
        return bannerService.findBannerByBannerId(bannerId)
    }
    fun findBannerByCompany(company: String):List<BannerDTO> {
        return bannerService.findBannerByCompany(company)
    }
    fun updateTitleByBannerId(bannerId: Int, title: String) {
        bannerService.updateTitleByBannerId(bannerId, title)
    }
    fun updateImagesUrlByBannerId(bannerId: Int, imagesUrl: String) {
        bannerService.updateImagesUrlByBannerId(bannerId, imagesUrl)
    }
    fun updateUrlByBannerId(bannerId: Int, url: String) {
        bannerService.updateUrlByBannerId(bannerId, url)
    }
}