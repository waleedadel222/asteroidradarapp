package com.waleed.asteroidradarapp.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomEntities::class], version = 2, exportSchema = false)

abstract class RoomDB : RoomDatabase() {
    abstract val roomDTO: RoomDTO

    companion object {
        @Volatile
        private lateinit var Instance: RoomDB
        fun getRoomDB(context: Context): RoomDB {
            synchronized(RoomDatabase::class.java) {
                if (!::Instance.isInitialized) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "asteroidradar"
                    ).build()
                }
            }
            return Instance
        }
    }
}