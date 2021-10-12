package com.lazymindapps.mytask

import android.app.Application
import com.lazymindapps.mytask.db.TaskDatabase
import com.lazymindapps.mytask.repo.TaskRepo

class TaskApplication : Application() {

    val database by lazy { TaskDatabase.createDatabase(this) }
    val repo by lazy { TaskRepo(database) }
}