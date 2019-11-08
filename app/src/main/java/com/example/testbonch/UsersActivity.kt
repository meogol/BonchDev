package com.example.testbonch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testbonch.moduls.Adapters.albumsAdapter
import com.example.testbonch.moduls.Adapters.userAdapter
import com.example.testbonch.moduls.interfase.RetrofitFactory
import com.example.testbonch.moduls.interfase.UserRetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UsersActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        userRecyclerView= findViewById<RecyclerView>(R.id.recycler_user)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        val service = UserRetrofitFactory.makeRetrofitServise()

        CoroutineScope(Dispatchers.IO).launch {
            val responce = service.getUser()
            try {
                withContext(Dispatchers.Main){
                    if( responce.isSuccessful){
                        userRecyclerView.adapter = userAdapter(responce.body()!!, this@UsersActivity)
                    }else{
                        Log.e("UserActivity", "Error operation failed " +
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
