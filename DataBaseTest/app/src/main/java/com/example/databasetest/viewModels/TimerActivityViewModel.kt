package com.example.databasetest.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.databasetest.adapters.RecyclerViewAdapter
import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise
import com.example.databasetest.room.ExerciseRepository

class TimerActivityViewModel(val application: Application,exeId:Int):ViewModel(),RecyclerViewAdapter.RecyclerViewSupplier {
    var currExercise: Exercise
    var exeActivities: List<ExeActivity>
    val exericeRepository= ExerciseRepository(application)
    var currnetActivity=0
    init {
        currExercise=exericeRepository.getExercise(exeId)
        exeActivities=exericeRepository.getRelatedActivities(currExercise)
    }
    fun onNext()=currnetActivity++
    override fun getActivities(): List<ExeActivity> {
        return exeActivities
    }

    override fun getExersice(): Exercise {
       return  currExercise
    }
    fun canNext()=currnetActivity<exeActivities.size
    fun getCurrentActivity()=exeActivities[currnetActivity]
    fun onReset(){
        exeActivities=exericeRepository.getRelatedActivities(currExercise)
        currnetActivity=0
    }
    fun onPrev(){
        currnetActivity--
    }
}