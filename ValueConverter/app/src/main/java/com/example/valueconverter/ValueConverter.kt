package com.example.valueconverter

interface ValueConverter{

    fun SetSourceMetric(metric:String):Unit
    fun SetTargetMetric(mectic:String):Unit
    fun Convert(number:Double):Double
}