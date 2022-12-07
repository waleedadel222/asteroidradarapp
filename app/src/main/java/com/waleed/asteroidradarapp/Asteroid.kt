package com.waleed.asteroidradarapp

import android.os.Parcelable
import com.waleed.asteroidradarapp.roomDB.RoomEntities
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(

    val id: Long,
    val codeName: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable {


}

fun ArrayList<Asteroid>.asDomainModel(): Array<RoomEntities> {
    return map {
        RoomEntities(
            id = it.id,
            codename = it.codeName,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
        .toTypedArray()
}