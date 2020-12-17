package com.example.databasetest.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.databasetest.room.Exercise

@Entity(tableName = "activities",
foreignKeys = [ForeignKey(onDelete = CASCADE,entity = Exercise::class,
parentColumns = arrayOf("id"),childColumns = arrayOf("exeId")) ])
data class ExeActivity (
        var title:Int,
        var description:String,
        var duration: Int,
        var repitations:Int,
        var exeId:Int,
        var number:Int,
        val pict:Int,
        var rest:Int,
        @PrimaryKey(autoGenerate = true) val id:Int?=null
)
