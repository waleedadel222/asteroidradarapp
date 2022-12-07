package com.waleed.asteroidradarapp.api

import com.waleed.asteroidradarapp.Constants.API_KEY
import com.waleed.asteroidradarapp.PictureOfDay
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("neo/rest/v1/feed")
    fun getAllAsteroidsByTimeAsync(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Deferred<ResponseBody>

    @GET("planetary/apod")
    fun getPictureOfDayAsync(
        @Query("api_key") apiKey: String = API_KEY
    ): Deferred<PictureOfDay>
}