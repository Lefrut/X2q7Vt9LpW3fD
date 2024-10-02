package com.effective.utils.network

sealed class ServiceException(message: String) : Exception(message)

class BadRequestException : ServiceException("Forbidden 400")
class ForbidenException : ServiceException("Forbidden 403")
class NotFoundException : ServiceException("Resource not found 404")
class UnauthorizedException : ServiceException("Unauthorized access 401")
class InternalServerException : ServiceException("Internal Server Error 500")
class NotImplementedServerException : ServiceException("Not Implemented 501")
class ServerException : ServiceException("Unknown server error 502 > 599")
class UnknownException : ServiceException("Unknown error")

fun getServiceExceptionByCode(code: Int): ServiceException {
    return when (code) {
        400 -> BadRequestException()
        403 -> ForbidenException()
        404 -> NotFoundException()
        401 -> UnauthorizedException()
        500 -> InternalServerException()
        501 -> NotImplementedServerException()
        in 502..599 -> ServerException()
        else -> UnknownException()
    }
}