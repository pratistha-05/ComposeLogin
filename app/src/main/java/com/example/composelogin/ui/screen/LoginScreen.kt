package com.example.composelogin.ui.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composelogin.ui.viewModel.LoginViewModel

@Composable
fun UserInputScreen(
  viewModel: LoginViewModel = hiltViewModel(),
//  onSubmit: (String, Int, String, String) -> Unit
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Text(
      text = "User Details",
      modifier = Modifier
        .padding(top = 20.dp)
        .align(Alignment.CenterHorizontally)
    )

    OutlinedTextField(
      value = uiState.name,
      onValueChange = { uiState.name = it },
      label = { Text("Name") },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = uiState.age,
      onValueChange = { uiState.age = it },
      label = { Text("Age") },
      keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = uiState.dob,
      onValueChange = { uiState.dob = it },
      label = { Text("Date of Birth") },
      readOnly = true,
      trailingIcon = {
        IconButton(onClick = { uiState.showDatePicker = true }) {
          Icon(imageVector = Icons.Default.DateRange, contentDescription = "Pick Date")
        }
      },
      modifier = Modifier.fillMaxWidth()
    )

//    if (uiState.showDatePicker) {
//      DatePickerDialog(
//        onDismissRequest = { uiState.showDatePicker = false },
//        onDateSelected = { selectedDate ->
//          uiState.dob = selectedDate
//          uiState.showDatePicker = false
//        }
//      )
//    }

    OutlinedTextField(
      value = uiState.address,
      onValueChange = { uiState.address = it },
      label = { Text("Address") },
      modifier = Modifier.fillMaxWidth()
    )

    Button(
      onClick = {
        if (uiState.name.isNotBlank() &&
          uiState.age.isNotBlank() &&
          uiState.dob.isNotBlank() &&
          uiState.address.isNotBlank()
        ) {
//          onSubmit(
//            uiState.name,
//            uiState.age.toIntOrNull() ?: 0,
//            uiState.dob,
//            uiState.address
//          )
        }
      },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text(text = "Submit")
    }
  }
}
