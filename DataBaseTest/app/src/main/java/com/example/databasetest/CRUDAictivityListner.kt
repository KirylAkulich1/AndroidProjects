package com.example.databasetest

import com.example.databasetest.room.ExeActivity

interface CRUDAictivityListner {
    fun onDelete(activity: ExeActivity)
    fun update(activity: ExeActivity)
}