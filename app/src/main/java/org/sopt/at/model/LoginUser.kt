package org.sopt.at.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUser(
    val id: String = "",
    val password: String = ""
): Parcelable
