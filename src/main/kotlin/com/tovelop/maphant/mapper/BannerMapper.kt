package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BannerDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BannerMapper{
    fun insertBanner(bannerDTO: BannerDTO)
    fun deleteBanner(bannerId: Int)
    fun findBannerByBannerId(bannerId: Int): BannerDTO
    fun findBannerByCompany(company: String): List<BannerDTO>
    fun updateTitleByBannerId(bannerId: Int, title: String)

    fun updateImagesUrlByBannerId(bannerId: Int, imagesUrl: String)
    fun updateUrlByBannerId(bannerId: Int, url: String)
}
