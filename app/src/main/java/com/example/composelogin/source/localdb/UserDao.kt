package com.example.composelogin.source.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
  @Query("SELECT * FROM login_database")
  fun getAllUsers(): Flow<List<UserData>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertUserData(data: UserData)

}