package org.sopt.at.remote

import org.json.JSONObject
import org.sopt.at.remote.base.BaseResponse
import retrofit2.HttpException

object ErrorHandler {
    // Throwable의 확장 함수로 에러 메시지 처리
    fun Throwable.handleError(): BaseResponse {
        return when (this) {
            is HttpException -> {
                val errorBody = this.response()?.errorBody()?.string().toString()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: Exception) {
                    "Unknown HTTP error: $e"
                }
                BaseResponse(
                    code = code().toString(),
                    message = errorMessage,
                    success = false
                )
            }
            else -> {
                BaseResponse(
                    code = "",
                    message = message ?: "Unknown error",
                    success = false
                )
            }
        }
    }
}