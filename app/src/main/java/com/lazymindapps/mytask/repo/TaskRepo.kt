package com.lazymindapps.mytask.repo

import androidx.lifecycle.LiveData
import com.lazymindapps.mytask.db.TaskDatabase
import com.lazymindapps.mytask.db.dao.TaskDao
import com.lazymindapps.mytask.db.model.Task

class TaskRepo(private val db: TaskDatabase) {


    suspend fun insertTask(task: Task) = db.getTaskDao().insertTask(task)

    suspend fun deleteTask(task:Task) = db.getTaskDao().deleteTask(task)

     fun getAllTask(): LiveData<List<Task>> = db.getTaskDao().getAllTask()
}