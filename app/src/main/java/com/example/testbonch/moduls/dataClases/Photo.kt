package com.example.testbonch.moduls.dataClases

data class Photo( val id: Int, val albumId: Int, val title: String, val url: String,
                   val thumbnailUrl: String)

class PhotosLab{
    val photosList: MutableList<Photo> = mutableListOf()

}