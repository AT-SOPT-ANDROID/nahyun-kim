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
import org.sopt.at.domain.usecase.GetUserNameUseCase
import org.sopt.at.presentation.my.state.MyState
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val clearUserInfoUseCase: ClearUserInfoUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState: StateFlow<UiState<Unit>>
        get() = _uiState.asStateFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            getUserNameUseCase().collect { userId ->
                if (userId == null) {
                    _uiState.value = UiState.Error(
                        message = "회원 정보가 없습니다."
                    )
                    return@collect
                }
                _uiState.value = UiState.Success(Unit)
                _state.value = _state.value.copy(
                    userId = userId
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