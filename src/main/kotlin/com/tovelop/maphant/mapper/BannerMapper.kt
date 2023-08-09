package com.tovelop.maphant.mapper

import com.tovelop.maphant.dto.BannerDTO
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BannerMapper{
    fun insertBanner(bannerDTO: BannerDTO)
}
