package org.sopt.at.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.state.UiState
import org.sopt.at.domain.usecase.SearchUserNicknameUseCase

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserNicknameUseCase: SearchUserNicknameUseCase
): ViewModel() {
    private val _searchWord = MutableStateFlow("")
    val searchWord: StateFlow<String>
        get() = _searchWord.asStateFlow()

    private val _searchResult = MutableStateFlow(listOf<String>())
    val searchResult: StateFlow<List<String>>
        get() = _searchResult.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val uiState: StateFlow<UiState<Unit>>
        get() = _uiState.asStateFlow()

    fun searchNickname() {
        viewModelScope.launch {
            _searchResult.value = searchUserNicknameUseCase.invoke(_searchWord.value)
        }
    }

    fun updateSearchNickname(searchWord: String) {
        _searchWord.value = searchWord
    }
}