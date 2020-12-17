package com.example.databasetest

import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise

interface ActivityListListner {
    fun onViewClicked(exercise: ExeActivity)
}