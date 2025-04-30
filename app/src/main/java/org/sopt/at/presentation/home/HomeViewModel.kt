package org.sopt.at.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.presentation.home.navigation.Home

class HomeViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val profile = savedStateHandle.toRoute<Home>()

    private val _selectedTabIndex = MutableStateFlow<Int?>(null)
    val selectedTabIndex: StateFlow<Int?>
        get() = _selectedTabIndex.asStateFlow()

    fun selectTab(index: Int?) {
        _selectedTabIndex.value = index
    }
}

