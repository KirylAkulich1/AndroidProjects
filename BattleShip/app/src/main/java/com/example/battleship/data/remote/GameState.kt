package com.example.battleship.data.remote

data class GameState(
    val gameId:String,
    val playr1Id:Int,
    val player2Id:Int,
    val x1:Int,
    val y1:Int,
    val x2:Int,
    val y2:Int
)