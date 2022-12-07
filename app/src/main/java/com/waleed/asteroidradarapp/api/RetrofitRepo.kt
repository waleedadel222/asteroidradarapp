package com.waleed.asteroidradarapp.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.waleed.asteroidradarapp.Asteroid
import com.waleed.asteroidradarapp.asDomainModel
import com.waleed.asteroidradarapp.roomDB.RoomDB
import com.waleed.asteroidradarapp.roomDB.RoomEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.N)
class RetrofitRepo(private val database: RoomDB) {
    lateinit var list: ArrayList<RoomEntities>

    suspend fun refreshAsteroids(
        startDate: String = getToday(),
        endDate: String = getSeventhDay()
    ) {
        var networkList: ArrayList<Asteroid>
        withContext(Dispatchers.IO) {
            val body: ResponseBody =
                RetrofitClient.AsteroidApi.retrofitInterface.getAllAsteroidsByTimeAsync(
                    startDate,
                    endDate
                )
                    .await()

            networkList = parseAsteroidsJsonResult(JSONObject(body.string()))

            database.roomDTO.insertAll(*networkList.asDomainModel())
        }
    }

    suspend fun deletePreviousDayAsteroids() {
        withContext(Dispatchers.IO) {
            database.roomDTO.deletePreviousDayAsteroids(getToday())
        }
    }
}