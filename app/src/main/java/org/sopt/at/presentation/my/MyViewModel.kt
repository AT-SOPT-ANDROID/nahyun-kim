package org.sopt.at.presentation.my

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import org.sopt.at.presentation.my.navigation.My

class MyViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val profile = savedStateHandle.toRoute<My>()
}