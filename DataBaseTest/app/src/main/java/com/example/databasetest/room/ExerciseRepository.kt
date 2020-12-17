package com.example.databasetest.room

import android.app.Application
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ExerciseRepository(application:Application):CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var exerciseDao:ExerciseDao
    private var activityDao:ActivityDao

    init{
        val db=AppDatabase.getInstance(application)
        exerciseDao=db.exerciseDao()
        activityDao=db.activityDao()


    }
    fun getExercises()=launch{exerciseDao.getExercises()}
    fun getLastExercise()=
        runBlocking {
            return@runBlocking getLastBG()
        }
    fun removeExercise(exe:Exercise) {
        launch { deleteExercise(exe) }
    }
    private suspend fun deleteExercise(exe:Exercise){
        withContext(Dispatchers.IO) { exerciseDao.delete(exe) }
    }

    fun addExercise(exe:Exercise)
    {
        launch {
            addExerciseBG(exe)
        }
    }
    private suspend fun getLastBG(): Exercise? {
        var exe:Exercise?=null
        withContext(Dispatchers.IO){
            exe=exerciseDao.getLastExercise()
        }
        return exe
    }
    private suspend fun addExerciseBG(exe:Exercise)
    {
        withContext(Dispatchers.IO){
            exerciseDao.insert(exe)
        }

    }

    fun getActivities()= runBlocking {
        return@runBlocking withContext(Dispatchers.IO)
        {
          return@withContext activityDao.getExercises()
        }
    }

    fun addActivity(activity:ExeActivity)
            {
                launch {
                    addActivityBG(activity)
                }
            }


    private suspend fun addActivityBG(activity:ExeActivity){
        withContext(Dispatchers.IO){
            activityDao.insert(activity)
        }
    }


    fun getRelatedActivities(exe:Exercise)= runBlocking {
        return@runBlocking withContext(Dispatchers.IO){
            return@withContext activityDao.getRelatedActivities(exe.id!!)
        }
    }

    fun insertAll(activities:List<ExeActivity>){
        launch {
            insertAllBG(activities)
        }
    }
    private suspend fun insertAllBG(activities: List<ExeActivity>){
        withContext(Dispatchers.IO){
            activityDao.insertAll(activities)
        }
    }
    fun getExerciseCount()=
        runBlocking {
            return@runBlocking withContext(Dispatchers.IO) {
                return@withContext exerciseDao.getExeCount()
            }
        }
    fun getExersiceList()=runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext exerciseDao.getExercisesList()
        }
    }
    fun getRelatedActivitiesN(exeId:Int,N:Int)=runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext activityDao.getRelatedActivitiesN(exeId,N)
        }
    }
    fun getExercise(exeId:Int)=runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext exerciseDao.getExersice(exeId)
        }
    }
    fun removeActivity(exeActivity:ExeActivity)=runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext activityDao.delete(exeActivity)
        }
    }
    fun updateActivity(activity:ExeActivity)=
            launch {
                updateActivityBG(activity)
            }
    suspend fun updateActivityBG(activity:ExeActivity){
        withContext(Dispatchers.IO)
        {
            activityDao.update(activity)
        }
    }
    fun clean_all_data()= runBlocking  {
        withContext(Dispatchers.IO){
            exerciseDao.clean_all()
        }
    }


}