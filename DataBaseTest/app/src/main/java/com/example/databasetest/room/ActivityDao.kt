package com.example.databasetest.room

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activity:ExeActivity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(activities:List<ExeActivity>)
    @Update
    fun update(activity:ExeActivity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(activities:MutableList<ExeActivity>)

    @Delete
    fun delete(activity:ExeActivity)

    @Query("delete from activities")
    fun deleteAllExe()

    @Query("SELECT * from activities ORDER BY number")
    fun getExercises(): LiveData<List<ExeActivity>>
    @Query("SELECT * from activities WHERE exeId=:exeId")
    fun getRelatedActivities(exeId:Int):List<ExeActivity>

    @Query("SELECT * from activities WHERE exeId=:exeId LIMIT :N")
    fun getRelatedActivitiesN(exeId:Int,N:Int):List<ExeActivity>


}