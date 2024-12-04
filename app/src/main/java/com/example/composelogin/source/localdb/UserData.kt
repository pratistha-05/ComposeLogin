package com.example.composelogin.source.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_database")//TODO: add this to constants
data class UserData(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val age: String,
  val name: String,
  val address: String,
  val dob: String
)