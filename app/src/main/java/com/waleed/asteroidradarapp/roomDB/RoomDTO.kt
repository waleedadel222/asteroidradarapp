package com.waleed.asteroidradarapp.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDTO {
    @Query("SELECT * FROM RoomEntities WHERE closeApproachDate >= :startDate AND closeApproachDate <= :endDate ORDER BY closeApproachDate ASC")
    fun getAsteroidsByCloseApproachDate(startDate: String, endDate: String): Flow<List<RoomEntities>>

    @Query("SELECT * FROM RoomEntities ORDER BY closeApproachDate ASC")
    fun getAllAsteroids(): Flow<List<RoomEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: RoomEntities)

    @Query("DELETE FROM RoomEntities WHERE closeApproachDate < :today")
    fun deletePreviousDayAsteroids(today: String): Int
}