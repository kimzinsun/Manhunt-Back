package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.BlockMapper
import org.springframework.stereotype.Service

@Service
class BlockService(
    private val blockMapper: BlockMapper
) {
    fun block(blockedId: Int, blockerId:Int){

        blockMapper.block(blockedId, blockerId)
    }
}