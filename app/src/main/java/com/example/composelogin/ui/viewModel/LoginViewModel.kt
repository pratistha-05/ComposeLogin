package com.example.composelogin.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.composelogin.data.InputUiState
import com.example.composelogin.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: LoginRepository,
  ) : ViewModel() {
  private val _uiState = MutableStateFlow(InputUiState())
  val uiState = _uiState.asStateFlow()

  fun onNameChange(newName: String) {
    _uiState.update { it.copy(name = newName) }
  }

  fun onAgeChange(newAge: String) {
    _uiState.update { it.copy(age = newAge) }
  }

  fun onShowDatePicker() {
    _uiState.update { it.copy(showDatePicker = !_uiState.value.showDatePicker) }
  }

  fun onDatePicked(newDob: String) {
    _uiState.update { it.copy(dob = newDob, showDatePicker = false) }
  }

  fun onAddressChange(newAddress: String) {
    _uiState.update { it.copy(address = newAddress) }
  }
}