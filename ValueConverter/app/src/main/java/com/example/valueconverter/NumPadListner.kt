package com.example.valueconverter

interface NumPadListner{
    fun onNumberClicked(ch:Char):Unit
    fun onEraseClicked():Unit
    fun onEraseAllClicked():Unit
    fun onPointClicked():Unit
    fun onSignChangedClicked():Unit

}