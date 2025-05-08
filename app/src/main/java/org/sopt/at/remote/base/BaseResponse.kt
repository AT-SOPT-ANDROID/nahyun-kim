package org.sopt.at.remote.base

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    val success: Boolean = false,
    val code: String = "",
    val message: String = ""
)