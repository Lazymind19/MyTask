package com.lazymindapps.mytask.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lazymindapps.mytask.db.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)


    @Query("Select * from tbl_task")
     fun getAllTask() : LiveData<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("Update tbl_task set task=:task, description=:description where id=:sn")
    suspend fun updateTask(task:String,description:String,sn:Int)


}