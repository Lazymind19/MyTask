package com.lazymindapps.mytask.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lazymindapps.mytask.db.TaskDatabase
import com.lazymindapps.mytask.db.model.Task
import com.lazymindapps.mytask.repo.TaskRepo

class TaskViewModel (val repo:TaskRepo ) : ViewModel() {
    var allTaskList : LiveData<List<Task>>?=null



    suspend fun insertTask(task: Task) = repo.insertTask(task)

    suspend fun deleteTask(task: Task) = repo.deleteTask(task)

     fun getAllTask():LiveData<List<Task>>{
        allTaskList = repo.getAllTask()
        return allTaskList as LiveData<List<Task>>
//
//
}

}