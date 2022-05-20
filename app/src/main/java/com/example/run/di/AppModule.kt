package com.example.run.di

import android.content.Context
import androidx.room.Room
import com.example.run.appconstants.AppConstants.DatabaseName
import com.example.run.roomdb.AppDao
import com.example.run.roomdb.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context:Context): AppDatabase {
    return Room.databaseBuilder(context,
        AppDatabase::class.java,
        DatabaseName).build()
    }
    @Singleton
    @Provides
    fun providesDao(db:AppDatabase): AppDao {
        return db.getDao()
    }


}