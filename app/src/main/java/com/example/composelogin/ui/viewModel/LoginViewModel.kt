package com.example.composelogin.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.composelogin.model.InputUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {
  private val _uiState = MutableStateFlow(InputUiState())
  val uiState = _uiState.asStateFlow()
}