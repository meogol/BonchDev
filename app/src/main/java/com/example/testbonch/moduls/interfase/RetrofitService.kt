package com.example.testbonch.moduls.interfase

import android.icu.text.CaseMap
import com.example.testbonch.moduls.dataClases.Albums
import com.example.testbonch.moduls.dataClases.Photo
import com.example.testbonch.moduls.dataClases.Post
import com.example.testbonch.moduls.dataClases.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Call
import retrofit2.http.*


interface AlbumsRetrofitService {
    @GET("/albums?userId=1")
    suspend fun getAlbums(): Response<List<Albums>>

    @DELETE("/albums/{id}")
    suspend fun deleteAlbums(@Path("id") id: String): Response<Albums>
}

interface UserRetrofitService{
    @GET("/users")
    suspend fun getUser(): Response<List<User>>

}

interface postsRetrofitService{
    @FormUrlEncoded
    @POST("/posts")
    suspend fun TransferToFragment(@Field ("title") title:String,
                                   @Field ("body") body:String): Response<Post>
}

interface PhotosRetrofitService{
    @GET("/photos?albumId=1")
    suspend fun getPhotos(): Response<List<Photo>>
}

object RetrofitFactory{
    fun makeRetrofitServise(): AlbumsRetrofitService{
        val retrofit= Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(AlbumsRetrofitService::class.java)
    }
}

object PostRetrofitFactory{
    fun makeRetrofitServise(): postsRetrofitService{
        val retrofit= Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(postsRetrofitService::class.java)
    }
}

object UserRetrofitFactory{
    fun makeRetrofitServise(): UserRetrofitService{
        val retrofit= Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(UserRetrofitService::class.java)
    }
}

object PhotoRetrofitFactory{
    fun makeRetrofitServise(): PhotosRetrofitService{
        val retrofit= Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(PhotosRetrofitService::class.java)
    }
}