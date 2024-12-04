package com.example.composelogin.data.repository

import com.example.composelogin.source.localdb.UserDao
import com.example.composelogin.source.localdb.UserData
import javax.inject.Inject

class LoginRepository @Inject constructor(
  private val dao: UserDao){

  val usersList = dao.getAllUsers()

  suspend fun insertUserData(userData: UserData){
    dao.insertUserData(userData)
  }

}