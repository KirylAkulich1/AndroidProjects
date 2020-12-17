package com.example.databasetest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "exercise_table")
data class Exercise(
        var color:Int,
        val description:String,
        var name:String,
        @PrimaryKey(autoGenerate = true) val id:Int?=null
)