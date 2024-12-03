package com.example.composelogin.source.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_database")
data class UserData(
  @PrimaryKey
  val id: String,
  val age: String,
  val name: String,
  val email: String,
  val address: String,
  val dob: String
)