package com.tovelop.maphant.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BlockMapper {

    fun block(blockedId: Int, blockerId: Int):Boolean


    fun deleteBlock(blockerId:Int, blockedId:Int) : Boolean

    fun getBlockCount(blockerId:Int, blockedId:Int): Int
}