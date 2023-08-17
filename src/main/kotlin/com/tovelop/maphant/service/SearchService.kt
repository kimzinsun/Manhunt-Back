package com.tovelop.maphant.service

import com.tovelop.maphant.mapper.SearchWordInverseMapper
import com.tovelop.maphant.mapper.SearchWordMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchService(private val searchWordMapper: SearchWordMapper,
                    private val searchWordInverseMapper: SearchWordInverseMapper) {

    @Transactional
    fun create(boardId:Int, title:String, content:String, tags:List<String> ) {
        /**
         * 1. 연속된 2글자씩 자르기
         *      -  같은 글자들은 횟수(tf) 세주기
         * 2. search_word 에 저x장
         *      - 해당 테이블에 없는 데이터면 df는 1
         *      - 해당 테이블에 있는 데이터면 df + 1
         *      - return 되는 값이 search_word_id
         *      - 구한 결과를 search_word_inverse 테이블에 저장
         *            - tf -> 1에서 구한 tf로 저장
         */
    }

    fun search(keyword:String) {
        /**
         * 1. keyword 연속된 2글자씩 자르기 (구한 2글자를 토근이란 명칭이로 가정)
         * 2. 토큰을 search_word select -> id, word, df 값이 구해짐
         * 3. search_word_df 테이블도 select (where search_word_id)
         *         - board_id, tf
         * 4. 각 board_id마다 tf * idf(구해주면서)
         * 5. 토큰마다 구한 tf*idf를 board_id마다 합산
         * 6. 내림차순으로 정렬 후 반환
         */
        val wordNMap = splitAndCount(keyword)

    }

    fun splitAndCount(input: String): Map<String, Int> {
        val wordMap = mutableMapOf<String, Int>()

        input.lowercase().trim().split(" ").forEach { word ->
            for (i in 0 until word.length - 1) {
                val twoChar = word.substring(i, i + 2)
                wordMap[twoChar] = wordMap.getOrDefault(twoChar, 0) + 1
            }
        }

        return wordMap
    }
}