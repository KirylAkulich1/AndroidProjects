package com.example.databasetest.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Exercise::class,ExeActivity::class],version = 8)
abstract class AppDatabase:RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun activityDao(): ActivityDao
    companion object{
        private var instance: AppDatabase?=null
        @Synchronized
        fun getInstance(ctx:Context): AppDatabase {
            if(instance ==null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            return instance!!
        }
        private val roomCallback=object :Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

}