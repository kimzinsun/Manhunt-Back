package com.tovelop.maphant.utils

class ValidationHelper {
    companion object {
        fun isAlphaNumeric(str: String): Boolean {
            return str.matches(Regex("^[a-zA-Z0-9]*$"))
        }

        fun isAlphaNumericKorean(str: String): Boolean {
            return str.matches(Regex("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$"))
        }

        fun isValidEmail(email: String): Boolean {
            return email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"))
        }

        fun isValidPassword(password: String): Boolean {
            return password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[a-zA-Z\\d@\$!%*?&]{8,}$"))
        }

        fun isValidPhoneNum(phoneNum: String): Boolean {
            return phoneNum.matches(Regex("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$"))
        }

        fun isValidNickname(nickname: String): Boolean {
            return nickname.matches(Regex("^[a-zA-Z0-9가-힣_-]{3,20}$"))
        }

        fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[가-힣]{2,10}$"))
        }

        fun isUniversityEmail(email: String): Boolean {
            return email.matches(Regex("^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9-]+\\.)+(ac.kr)$")) || email.matches(Regex("^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9-]+\\.)+(edu)$"))
        }
    }
}