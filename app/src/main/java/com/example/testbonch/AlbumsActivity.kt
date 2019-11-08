package com.example.testbonch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testbonch.moduls.Adapters.albumsAdapter
import com.example.testbonch.moduls.dataClases.Albums
import com.example.testbonch.moduls.interfase.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class AlbumsActivity : AppCompatActivity() {

    private lateinit var albumsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        albumsRecyclerView= findViewById<RecyclerView>(R.id.recycler_albums)

        albumsRecyclerView.layoutManager = LinearLayoutManager(this)
        val service = RetrofitFactory.makeRetrofitServise()

        CoroutineScope(Dispatchers.IO).launch {
            val responce = service.getAlbums()
            try {
                withContext(Dispatchers.Main){
                    if( responce.isSuccessful){
                        val adapter = albumsAdapter(responce.body()!!, this@AlbumsActivity)
                        albumsRecyclerView.adapter = adapter

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

    }


}
