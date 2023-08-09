package com.tovelop.maphant.utils

class BadWordFiltering {
    var badWords = listOf<String>()

    fun filter(text: String): String {
        var result = text
        badWords.forEach { badWord ->
            result = result.replace(badWord, "*".repeat(badWord.length))
        }
        return result
    }

    fun addBadWord(badWord: String) {
        badWords += badWord
    }

    fun removeBadWord(badWord: String) {
        badWords -= badWord
    }

    fun clearBadWords() {
        badWords = listOf()
    }

    fun getBadWords(): List<String> {
        return badWords
    }

    fun setBadWords(badWords: List<String>) {
        this.badWords = badWords
    }

    fun getBadWordsCount(): Int {
        return badWords.size
    }

    fun isBadWord(text: String): Boolean {
        return badWords.contains(text)
    }
}
