package com.example.testbonch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.testbonch.moduls.interfase.PostRetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var usersButton: Button
    private lateinit var albumsButton: Button
    private lateinit var  photosButton: Button
    private lateinit var  postCreateDialogFragmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersButton = findViewById(R.id.user_button)
        albumsButton = findViewById(R.id.albums_button)
        photosButton = findViewById(R.id.photos_button)
        postCreateDialogFragmentButton = findViewById(R.id.post_create_dialog_fragment)

        usersButton.setOnClickListener{
            startActivity(Intent(this, UsersActivity().javaClass))
            finish()
        }

        albumsButton.setOnClickListener{
            startActivity(Intent(this, AlbumsActivity().javaClass))
            finish()
        }

        photosButton.setOnClickListener{
            startActivity(Intent(this, PhotosActivity().javaClass))
            finish()
        }

        postCreateDialogFragmentButton.setOnClickListener{
            val dialog = PostCreateDialogFragment(applicationContext)
            dialog.show(supportFragmentManager,"dialog")
        }

    }

}


