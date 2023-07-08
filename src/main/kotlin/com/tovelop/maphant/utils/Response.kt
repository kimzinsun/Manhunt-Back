package com.tovelop.maphant.utils

typealias ResponseUnit = Response<Unit>
interface Response<T> {
    val success: Boolean

    companion object {
        fun <T> success(data: T): Response<T> {
            return SuccessResponse(data)
        }

        fun stateOnly(isSuccess: Boolean): ResponseUnit {
            return UnitResponse(isSuccess)
        }

        fun <T> error(errors: Any): Response<T> {
            return ErrorResponse(errors)
        }
    }
}

fun Response<out Any>.isSuccess(): Boolean {
    return this.success
}

class SuccessResponse<T>(val data: T) : Response<T> {
    override val success: Boolean = true
}

class ErrorResponse<T>(val errors: Any) : Response<T> {
    override val success: Boolean = false
}

class UnitResponse(override val success: Boolean) : ResponseUnit