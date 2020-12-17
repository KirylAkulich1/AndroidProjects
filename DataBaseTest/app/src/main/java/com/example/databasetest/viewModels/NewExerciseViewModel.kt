package com.example.databasetest.viewModels

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.databasetest.ActivityListListner
import com.example.databasetest.ActivitySubsriber
import com.example.databasetest.CRUDAictivityListner
import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise
import com.example.databasetest.room.ExerciseRepository

class NewExerciseViewModel(application: Application):ViewModel(),ActivityListListner,CRUDAictivityListner {
    var currExercise: Exercise
    var activities= mutableListOf<ExeActivity>()
    val exericeRepository= ExerciseRepository(application)
    lateinit var activitySubsriber: ActivitySubsriber
    init {
        val exeId=exericeRepository.getExerciseCount()+1
        currExercise= Exercise(Color.WHITE,"","",exeId)
    }

    override fun onViewClicked(exercise: ExeActivity) {
        exercise.exeId=currExercise.id!!
        activities.add(exercise)
        activitySubsriber.onNext(exercise)
    }
    fun Save(name:String,color:Int){
        currExercise.name=name
        currExercise.color=color
        exericeRepository.addExercise(currExercise)
        exericeRepository.insertAll(activities)

    }
    fun subscribe(subsriber: ActivitySubsriber)
    {
        activitySubsriber=subsriber
    }


    override fun onDelete(activity: ExeActivity) {
        exericeRepository.removeActivity(activity)
    }

    override fun update(activity: ExeActivity) {
        exericeRepository.updateActivity(activity)
    }

}