package com.waleed.asteroidradarapp.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.waleed.asteroidradarapp.PictureOfDay
import com.waleed.asteroidradarapp.api.RetrofitClient
import com.waleed.asteroidradarapp.api.RetrofitRepo
import com.waleed.asteroidradarapp.roomDB.RoomDB
import com.waleed.asteroidradarapp.roomDB.RoomEntities
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.N)
class MainViewModel(private val db: RoomDB, app: Application) : AndroidViewModel(app) {

    private val repo = RetrofitRepo(db)

    private val _asteroidList = MutableLiveData<List<RoomEntities>>()
    val asteroidList: LiveData<List<RoomEntities>>
        get() = _asteroidList

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay


    init {
        viewModelScope.launch {
            repo.refreshAsteroids()
            getAsteroidList()
            try {
                getPictureOfDay()

            } catch (e: Exception) {
                return@launch
            }

        }
    }

    private suspend fun getAsteroidList() {

        db.roomDTO.getAllAsteroids().collect {
            _asteroidList.value = it
        }
    }

    private suspend fun getPictureOfDay() {
        val pictureOfDay =
            RetrofitClient.AsteroidApi.retrofitInterface.getPictureOfDayAsync().await()
        if (pictureOfDay.mediaType == "image") {
            _pictureOfDay.value = pictureOfDay
        }

    }

}
