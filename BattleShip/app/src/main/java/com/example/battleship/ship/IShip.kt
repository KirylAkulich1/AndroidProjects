package com.example.battleship.ship

enum class Result1{
        OK,Err
}
interface IShip {
    fun Place():Result1{
        return Result1.Err
    }
}