package com.example.testbonch.moduls.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testbonch.R
import com.example.testbonch.moduls.dataClases.Albums
import com.example.testbonch.moduls.interfase.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class albumsAdapter(var list: List<Albums>, val context: Context):
    RecyclerView.Adapter<albumsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_albums, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val albums = list[position]
        holder.bind(albums)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val service = RetrofitFactory.makeRetrofitServise()

            CoroutineScope(Dispatchers.IO).launch {
                val responce = service.deleteAlbums(albums.id.toString())
                try {
                    withContext(Dispatchers.Main){
                        if( responce.isSuccessful){
                            responce.message()
                            removeItem(position)
                        }else{
                            Log.e("AlbumsActivity", "Error operation failed " +
                                    "with ${responce.code()}")
                        }
                    }
                }catch (e: HttpException){
                    Log.e("Request", "Exception ${e.message()}")
                }
                catch (e: Throwable){
                    Log.e("Request", "Something went wrong!")

                }
            }

        })
    }

    private fun removeItem(position: Int){
        list = list.drop(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val idTV = itemView.findViewById<TextView>(R.id.tv_ID)
        private val userIdTV = itemView.findViewById<TextView>(R.id.tv_userID)
        private val titleTV =  itemView.findViewById<TextView>(R.id.tv_title)


        fun bind(albums: Albums){
            idTV.text=albums.id.toString()
            userIdTV.text=albums.userId.toString()
            titleTV.text=albums.title
        }
    }
}