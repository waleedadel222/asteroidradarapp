package com.waleed.asteroidradarapp.roomDB

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class RoomEntities(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable {

    class AsteroidDiffUtil : DiffUtil.ItemCallback<RoomEntities>() {
        override fun areItemsTheSame(oldItem: RoomEntities, newItem: RoomEntities): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoomEntities, newItem: RoomEntities): Boolean {
            return oldItem == newItem
        }
    }
}
