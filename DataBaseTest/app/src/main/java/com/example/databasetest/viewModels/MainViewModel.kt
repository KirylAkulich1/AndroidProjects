package com.example.databasetest.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databasetest.ActivityListListner
import com.example.databasetest.ActivitySubsriber
import com.example.databasetest.CRUDAictivityListner
import com.example.databasetest.ExersiceActivity
import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise
import com.example.databasetest.room.ExerciseRepository
import kotlinx.coroutines.*

class MainViewModel(val application: Application):ViewModel(),ActivityListListner,CRUDAictivityListner {

    var exerciseRepository: ExerciseRepository
    lateinit var subscriber:ActivitySubsriber
    var exersice:Exercise?
    val dispatcher= newFixedThreadPoolContext(2,"Init")
    init{
        exerciseRepository = ExerciseRepository(application)
        exersice = exerciseRepository.getLastExercise()

        if (exersice == null) {
            exersice = Exercise(1, "MyFirstExerscise", "Init",id = 1)
            exerciseRepository.addExercise(exersice!!)

        }
    }

    fun initActivity()=
        viewModelScope.launch(Dispatchers.IO) {
            delay(500L)

        }
    fun getTitle()=exersice?.name


    override fun onViewClicked(activity:ExeActivity) {
        activity.exeId= exersice!!.id!!
        exerciseRepository.addActivity(activity)
       subscriber.onNext(activity)
    }
    fun renewExercise(exeId:Int){
        exersice=exerciseRepository.getExercise(exeId)
    }
    override fun onCleared() {
        super.onCleared()

    }
    fun getActivities()=exerciseRepository.getActivities()

    fun addExercise(exe:Exercise){
        exerciseRepository.addExercise(exe)
    }
    fun addActivity(activity:ExeActivity)
    {
        exerciseRepository.addActivity(activity)
    }
    fun subscibe(sub:ActivitySubsriber)
    {
        subscriber=sub
        val exercises=exerciseRepository.getRelatedActivities(exersice!!)
        subscriber.insertValues(exercises,exersice!!)
    }

    override fun onDelete(activity: ExeActivity) {
        exerciseRepository.removeActivity(activity)
    }

    override fun update(activity: ExeActivity) {
        exerciseRepository.updateActivity(activity)
    }
    fun checkactivity():Boolean{
        exerciseRepository = ExerciseRepository(application)
        if(exersice==null)
        {
            exersice = Exercise(1, "MyFirstExerscise", "Init",id = 1)
            exerciseRepository.addExercise(exersice!!)
            return false
        }
        else {
            exersice = exerciseRepository.getExercise(exersice!!.id!!)
            if (exersice == null) {
                exersice = Exercise(1, "MyFirstExerscise", "Init", id = 1)
                exerciseRepository.addExercise(exersice!!)
                return false

            }
            else
                return true
        }
    }



}