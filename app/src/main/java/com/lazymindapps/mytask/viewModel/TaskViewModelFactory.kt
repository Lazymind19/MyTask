package com.lazymindapps.mytask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lazymindapps.mytask.repo.TaskRepo

class TaskViewModelFactory(val repo:TaskRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repo) as T
    }
}