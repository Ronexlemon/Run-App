package com.example.run.appviewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.run.apprepository.AppRepository
import com.example.run.roomdb.TimeStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
@HiltViewModel
@ExperimentalTime
class AppViewModel
 @Inject constructor(private val repo:AppRepository)   : ViewModel() {
    var getAll :LiveData<List<TimeStore>> = repo.getAllData()

   var week = Calendar.getInstance(TimeZone.getTimeZone("GMT")).get(Calendar.DAY_OF_WEEK)
    private var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")
    var isPlaying by mutableStateOf(false)

    fun start() {
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            time = time.plus(Duration.seconds(1))
            updateTimeStates()
        }
        isPlaying = true
    }

    private fun updateTimeStates() {
        time.toComponents { _,hours, minutes, seconds,_ ->
            this@AppViewModel.seconds = seconds.pad()
            this@AppViewModel.minutes = minutes.pad()
            this@AppViewModel.hours = hours.pad()
        }
    }

    private fun Int.pad(): String {
        return this.toString().padStart(2, '0')
    }

    fun pause() {
        timer.cancel()
        isPlaying = false
    }

    fun stop() {
        pause()
        time = Duration.ZERO
        updateTimeStates()
    }
    fun insert(){
        val hour = hours
        val minute = minutes
        val sec = seconds
        viewModelScope.launch {
            repo.insertData(TimeStore(hours=hour,id=0, minutes = minute, seconds = sec))
        }

    }
    fun delete(){
        viewModelScope.launch {
            repo.deleteData(seconds)
        }
    }
}