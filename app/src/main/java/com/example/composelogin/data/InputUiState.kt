package com.example.composelogin.data

data class InputUiState(
  var name: String = "",
  var age: String = "",
  var dob: String = "",
  var address: String = "",
  var showDatePicker: Boolean = false
)
