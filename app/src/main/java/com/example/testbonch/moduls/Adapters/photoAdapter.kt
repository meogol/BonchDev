package com.example.testbonch.moduls.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testbonch.R
import com.example.testbonch.moduls.dataClases.Albums
import com.example.testbonch.moduls.dataClases.Photo
import com.example.testbonch.moduls.dataClases.User

class photoAdapter(val list: List<Photo>, val context: Context):
    RecyclerView.Adapter<photoAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_photos, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val photo = list[position]
        holder.bind(photo)
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val idTV = itemView.findViewById<TextView>(R.id.tv_id)
        private val albumIdTV= itemView.findViewById<TextView>(R.id.tv_album_id)
        private val titleTV = itemView.findViewById<TextView>(R.id.tv_title)
        private val photosIV = itemView.findViewById<ImageView>(R.id.iv_photos)
        private val photos2TV = itemView.findViewById<ImageView>(R.id.iv_photo2)

        fun bind(photo: Photo){
            idTV.text=photo.id.toString()
            albumIdTV.text=photo.albumId.toString()
            titleTV.text=photo.title
            Glide.with(itemView.context).load(photo.url).into(photosIV)
            Glide.with(itemView.context).load(photo.thumbnailUrl).into(photos2TV)

        }
    }
}