package com.example.testbonch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testbonch.moduls.Adapters.albumsAdapter
import com.example.testbonch.moduls.Adapters.photoAdapter
import com.example.testbonch.moduls.interfase.PhotoRetrofitFactory
import com.example.testbonch.moduls.interfase.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class PhotosActivity : AppCompatActivity() {
    private lateinit var photosRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        photosRecyclerView= findViewById<RecyclerView>(R.id.recycler_photos)

        photosRecyclerView.layoutManager = LinearLayoutManager(this)
        val service = PhotoRetrofitFactory.makeRetrofitServise()

        CoroutineScope(Dispatchers.IO).launch {
            val responce = service.getPhotos()
            try {
                withContext(Dispatchers.Main){
                    if( responce.isSuccessful){
                        val adapter = photoAdapter(responce.body()!!, this@PhotosActivity)
                        photosRecyclerView.adapter = adapter

                    }else{
                        Log.e("PhotosActivity", "Error operation failed " +
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
