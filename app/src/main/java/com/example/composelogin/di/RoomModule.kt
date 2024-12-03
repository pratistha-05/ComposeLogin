package com.example.composelogin.di

import android.content.Context
import androidx.room.Room
import com.example.composelogin.source.localdb.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  @Provides
  @Singleton
  fun provideCoinDatabase(@ApplicationContext context: Context): UserDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      UserDatabase::class.java,
      "login_database"
    ).build()
  }
}