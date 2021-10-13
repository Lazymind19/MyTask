package com.lazymindapps.mytask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lazymindapps.mytask.db.dao.TaskDao
import com.lazymindapps.mytask.db.model.Task

@Database(entities = [Task::class],version = 3)
abstract  class TaskDatabase:RoomDatabase() {
    abstract fun getTaskDao():TaskDao

    companion object{
        private var INSTANCE :TaskDatabase?= null

        fun createDatabase(context: Context):TaskDatabase{
            return INSTANCE ?: synchronized(this){
                val instance =Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,

                    "task_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}