package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.BlockMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BlockService(private val blockMapper: BlockMapper) {

    @Transactional
    fun releaseBlock(blockerId:Int, blockedId:Int) {

        //blockerId가 blockedId를 차단한 적 있는지 확인
        val count = blockMapper.getBlockCount(blockerId, blockedId)

        if(count == 0)
            throw IllegalStateException("해당 유저를 차단한적이 없습니다.")

        blockMapper.deleteBlock(blockerId,blockedId)
    }
}