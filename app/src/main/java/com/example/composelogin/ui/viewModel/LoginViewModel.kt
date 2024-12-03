package com.example.composelogin.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.composelogin.data.InputUiState
import com.example.composelogin.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: LoginRepository,
  ) : ViewModel() {
  private val _uiState = MutableStateFlow(InputUiState())
  val uiState = _uiState.asStateFlow()


}