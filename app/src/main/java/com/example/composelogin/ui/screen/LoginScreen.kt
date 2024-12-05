package com.example.composelogin.ui.screen

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composelogin.source.localdb.UserData
import com.example.composelogin.ui.viewModel.LoginViewModel

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun UserInputScreen(
  viewModel: LoginViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

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
      onValueChange = { viewModel.onNameChange(it) },
      label = { Text("Name") },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = uiState.age,
      onValueChange = { viewModel.onAgeChange(it) },
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
        IconButton(onClick = { viewModel.onShowDatePicker() }) {
          Icon(imageVector = Icons.Default.DateRange, contentDescription = "Pick Date")
        }
      },
      modifier = Modifier.fillMaxWidth()
    )

    if (uiState.showDatePicker) {
      CalendarDatePicker(viewModel,context)
    }

    OutlinedTextField(
      value = uiState.address,
      onValueChange = { viewModel.onAddressChange(it) },
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
          val userDetails = UserData(
            name = uiState.name,
            age = uiState.age,
            dob = uiState.dob,
            address = uiState.address
          )
            viewModel.insertUserDetails(userDetails)
        }
        else{
          Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
        }
      },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text(text = "Submit")
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDatePicker(viewModel: LoginViewModel, context: Context) {
  val datePickerState = rememberDatePickerState()

  DatePickerDialog(
    onDismissRequest = { viewModel.onShowDatePicker() },
    confirmButton = {
      TextButton(
        onClick = {
          val selectedDateMillis = datePickerState.selectedDateMillis
          if (selectedDateMillis != null) {
            val selectedDate = LocalDate.ofInstant(
              Date(selectedDateMillis).toInstant(),
              ZoneId.systemDefault()
            )
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = selectedDate.format(formatter)
            viewModel.onShowDatePicker()
            viewModel.onDatePicked(formattedDate)
          }
          else{
            Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show()
          }
        }){
        Text("OK")
      }
    },

    dismissButton = {
      TextButton(onClick = { viewModel.onShowDatePicker() }) {
        Text("Cancel")
      }
    },
  ) {
    DatePicker(state = datePickerState)
  }
}
