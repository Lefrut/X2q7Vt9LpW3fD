package com.effective.utils.network

fun getErrorMessage(statusCode: Int): String {
    return when (statusCode) {
        400 -> "Bad Request"
        401 -> "Unauthorized"
        403 -> "Forbidden"
        404 -> "Not Found"
        500 -> "Internal Server Error"
        501 -> "Not Implemented"
        in 502..599 -> "Unknown server error"
        else -> "Unknown Error"
    }
}