package com.waleed.asteroidradarapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.waleed.asteroidradarapp.databinding.AsteroidItemBinding
import com.waleed.asteroidradarapp.roomDB.RoomEntities

class AsteroidAdapter(

    val itemClick: (RoomEntities) -> Unit
) :
    ListAdapter<RoomEntities, AsteroidAdapter.AsteroidViewHolder>(RoomEntities.AsteroidDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(this, parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindView(item)
    }


    class AsteroidViewHolder private constructor(
        private val view: AsteroidItemBinding,
        val itemClick: (RoomEntities) -> Unit
    ) :
        RecyclerView.ViewHolder(view.root) {

        fun bindView(item: RoomEntities) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                view.asteroid = item
            }
        }

        companion object {
            fun from(
                asteroidRecycleAdapter: AsteroidAdapter,
                parent: ViewGroup
            ): AsteroidViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view =
                    AsteroidItemBinding
                        .inflate(layoutInflater, parent, false)
                return AsteroidViewHolder(view, asteroidRecycleAdapter.itemClick)
            }
        }
    }


}
