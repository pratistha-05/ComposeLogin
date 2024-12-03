package com.example.composelogin.di

import com.example.composelogin.source.localdb.UserDao
import com.example.composelogin.source.localdb.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideDao {

  @Provides
  fun providesUserDap(appDatabase: UserDatabase): UserDao {
    return appDatabase.userDao()
  }
}