package com.example.run.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao

import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    @Insert
    suspend fun  insert(data:TimeStore)
    @Query("SELECT * FROM TimeStamp ")
    fun getAll(): LiveData<List<TimeStore>>
    @Query("DELETE from TimeStamp where seconds = :seconds")
    suspend fun  delete(seconds:String)

}