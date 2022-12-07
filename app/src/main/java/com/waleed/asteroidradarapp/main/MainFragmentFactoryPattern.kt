package com.waleed.asteroidradarapp.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waleed.asteroidradarapp.roomDB.RoomDB

class MainFragmentFactoryPattern(
    private val dB: RoomDB,
    private val application: Application
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
             return MainViewModel(dB, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}