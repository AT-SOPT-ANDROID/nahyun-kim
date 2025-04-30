package org.sopt.at.presentation.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetUserNameUseCase
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase
): ViewModel() {

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            getUserNameUseCase().collect { it ->
                _userName.value = it
            }
        }
    }
}