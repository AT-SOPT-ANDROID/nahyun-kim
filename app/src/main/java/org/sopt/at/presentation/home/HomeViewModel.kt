package org.sopt.at.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _selectedTabState = MutableStateFlow<TabState>(TabState.None)
    val selectedTabState: StateFlow<TabState>
        get() = _selectedTabState.asStateFlow()

    fun selectTab(tabState: TabState) {
        _selectedTabState.value = tabState
    }
}

sealed class TabState {
    object None : TabState()
    data class Selected(val index: Int) : TabState()
}