package com.example.testbonch.moduls.dataClases

data class Post( val id: Int, val userId: Int, val title: String, val body: String)

class PostLab{
    val albumsList: MutableList<Albums> = mutableListOf()

}