package com.example.testbonch.moduls.dataClases

data class Albums( val id: Int, val userId: Int, val title: String)

class AlbumsLab{
    val albumsList: MutableList<Albums> = mutableListOf()

}