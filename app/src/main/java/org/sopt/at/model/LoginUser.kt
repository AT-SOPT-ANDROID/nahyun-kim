package org.sopt.at.model

import java.io.Serializable

data class LoginUser(
    val id: String = "",
    val password: String = ""
): Serializable
