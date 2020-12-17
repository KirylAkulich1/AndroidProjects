package com.example.databasetest

import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise

interface ActivitySubsriber {
    fun onNext(activity:ExeActivity)
    fun insertValues(list:List<ExeActivity>,exercise: Exercise)
    fun renewItems(list:MutableList<ExeActivity>)
}