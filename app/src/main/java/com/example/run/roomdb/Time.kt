package com.example.run.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TimeStamp")
data class TimeStore(
    @ColumnInfo(name = "hours")
    val hours:String?=null,
    @ColumnInfo(name="user_id")
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    @ColumnInfo(name="minutes")
    val minutes:String?=null,
    @ColumnInfo(name="seconds")
     val seconds:String?=null)
