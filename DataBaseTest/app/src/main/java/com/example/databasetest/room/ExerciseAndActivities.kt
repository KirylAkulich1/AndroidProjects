package com.example.databasetest.room

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseAndActivities (
        @Embedded val exercise: Exercise,
        @Relation(
        parentColumn = "id",
        entityColumn = "exeId"
    )
    val activities:List<ExeActivity>
)