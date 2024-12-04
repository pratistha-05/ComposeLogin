package com.example.composelogin.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.data.InputUiState
import com.example.composelogin.data.repository.LoginRepository
import com.example.composelogin.source.localdb.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: LoginRepository,
  ) : ViewModel() {
  private val _uiState = MutableStateFlow(InputUiState())
  val uiState = _uiState.asStateFlow()

  private val _usersFlow = MutableStateFlow<List<UserData>>(emptyList())
  val usersFlow: StateFlow<List<UserData>> = _usersFlow.asStateFlow()

  init {
    viewModelScope.launch {
      repository.usersList.collect { userList ->
        _usersFlow.value = userList
        Log.d("LoginViewModel", "Users: $userList")
      }
    }
  }

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

  fun insertUserDetails(userData: UserData){
    viewModelScope.launch {
      Log.e("LoginViewModel", "Inserting user data: $userData")
      repository.insertUserData(userData)
    }
  }

}