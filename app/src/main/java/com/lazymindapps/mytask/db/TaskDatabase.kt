package com.lazymindapps.mytask.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lazymindapps.mytask.db.dao.TaskDao

abstract  class TaskDatabase:RoomDatabase() {
    abstract fun getTaskDao():TaskDao

    companion object{
        private var instance :TaskDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:createDatabase(context).also { instance= it}

        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "taskdb"
            ).build()




    }

}