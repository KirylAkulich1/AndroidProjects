package com.example.databasetest.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exe: Exercise)

    @Update
    fun update(exe: Exercise)

    @Delete
    fun delete(exe: Exercise)


    @Query("delete from exercise_table")
    fun deleteAllExe()

    @Query("SELECT * from exercise_table ORDER BY id")
    fun getExercises():LiveData<List<Exercise>>

    @Query("SELECT * from exercise_table ORDER BY id")
    fun getExercisesList():MutableList<Exercise>

    @Query("SELECT * from exercise_table ORDER BY  id DESC LIMIT 1")
    fun getLastExercise():Exercise?

    @Query("SELECT COUNT(*) FROM exercise_table")
    fun getExeCount():Int

    @Query("SELECT * FROM exercise_table WHERE id=:exeId")
    fun getExersice(exeId:Int):Exercise
    @Query("DELETE  FROM exercise_table")
    fun clean_all()

}