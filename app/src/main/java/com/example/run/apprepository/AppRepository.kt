package com.example.run.apprepository

import com.example.run.roomdb.AppDao
import com.example.run.roomdb.TimeStore
import javax.inject.Inject

class AppRepository
@Inject constructor(private val dao:AppDao){
    fun getAllData()= dao.getAll()
    suspend fun  insertData(data:TimeStore){
        dao.insert(data)
    }
    suspend fun  deleteData(sec:String){
        dao.delete(sec)
    }
}