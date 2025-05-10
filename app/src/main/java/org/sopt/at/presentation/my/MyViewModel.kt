package org.sopt.at.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.state.UiState
import org.sopt.at.domain.usecase.ClearUserInfoUseCase
import org.sopt.at.domain.usecase.EditNicknameUseCase
import org.sopt.at.domain.usecase.GetMyNicknameUseCase
import org.sopt.at.presentation.my.state.MyState
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getMyNicknameUseCase: GetMyNicknameUseCase,
    private val clearUserInfoUseCase: ClearUserInfoUseCase,
    private val editNicknameUseCase: EditNicknameUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    private val _profileEditState = MutableStateFlow(MyState())
    val profileEditState: StateFlow<MyState>
        get() = _profileEditState.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>>
        get() = _uiState.asStateFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            val response = getMyNicknameUseCase.invoke()
            if (response.success) {
                val nickname = response.result.nickname
                _state.value = _state.value.copy(
                    nickname = nickname
                )
                _profileEditState.value = _profileEditState.value.copy(
                    nickname = nickname
                )
            }
        }
    }

    fun updateNickname(nickname: String) {
        _profileEditState.value = _profileEditState.value.copy(
            nickname = nickname,
        )
    }

    fun onNicknameEdit() {
        val newNickname = _profileEditState.value.nickname
        viewModelScope.launch {
            val response = editNicknameUseCase.invoke(newNickname)
            if (response.success) {
                _uiState.value = UiState.Success(Unit)
                _state.value = _state.value.copy(
                    nickname = newNickname
                )
            } else {
                _uiState.value = UiState.Error(
                    message = response.message
                )
            }
        }
    }

    fun clearUserInfo() {
        viewModelScope.launch {
            clearUserInfoUseCase.invoke()
        }
    }
}