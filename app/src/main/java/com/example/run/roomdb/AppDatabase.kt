package com.example.run.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [TimeStore::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract  fun getDao() : AppDao
}