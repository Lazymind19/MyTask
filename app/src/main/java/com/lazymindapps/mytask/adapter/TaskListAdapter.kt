package com.lazymindapps.mytask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.lazymindapps.mytask.databinding.RvTasklistItemBinding
import com.lazymindapps.mytask.db.model.Task

class TaskListAdapter(val taskList:List<Task>) : RecyclerView.Adapter<TaskListAdapter.ViewHOlder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHOlder {

        val binding = RvTasklistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHOlder(binding)

    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHOlder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return taskList.size

    }

    class ViewHOlder( val itemBind: RvTasklistItemBinding) :RecyclerView.ViewHolder(itemBind.root){
        fun bind(task:Task){
            itemBind.tvTaskTitle.text = task.task
            itemBind.tvTaskDescription.text = task.description



        }

    }
}