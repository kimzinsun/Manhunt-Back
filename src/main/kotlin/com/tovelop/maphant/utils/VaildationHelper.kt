package com.tovelop.maphant.utils

class VaildationHelper {
    companion object {
        fun isAlphaNumeric(str: String): Boolean {
            return str.matches(Regex("^[a-zA-Z0-9]*$"))
        }
        fun isAlphaNumericKorean(str: String) : Boolean {
            return str.matches(Regex("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$"))
        }
        fun isEmailValid(email: String): Boolean {
            return email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
        }
        fun isPasswordValid(password: String): Boolean {
            return password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
        }
        fun isPhoneNumValid(phoneNum: String): Boolean {
            return phoneNum.matches(Regex("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$"))
        }
    }
}