package com.lazymindapps.mytask.ui

import TaskDeleteInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazymindapps.mytask.MainActivity
import com.lazymindapps.mytask.R
import com.lazymindapps.mytask.adapter.TaskListAdapter
import com.lazymindapps.mytask.databinding.FragmentTaskListBinding
import com.lazymindapps.mytask.db.model.Task
import com.lazymindapps.mytask.viewModel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TaskListFragment : Fragment(),CoroutineScope {
    lateinit var binding: FragmentTaskListBinding
    lateinit var viewModel: TaskViewModel
    var taskList:List<Task> = mutableListOf()
    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        fetchAllTask()


        binding.fbAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addOrUpdateTaskFragment)
        }

    }

    private fun fetchAllTask(){

        viewModel.getAllTask().observe(viewLifecycleOwner, Observer { tasks->
            if (tasks!=null) {
                taskList = tasks
                binding.rvTaskList.visibility = View.VISIBLE
                binding.tvNoTaskMessage.visibility = View.GONE
//                Toast.makeText(requireContext(),taskList.toString(),Toast.LENGTH_LONG).show()
                showTaskListRecyclerView(tasks)
            }
            else{
                binding.tvNoTaskMessage.visibility = View.VISIBLE
                binding.rvTaskList.visibility = View.GONE
            }



        })



    }

    private fun showTaskListRecyclerView(taskList:List<Task>){
        val layoutManager = LinearLayoutManager(context)
        binding.rvTaskList.layoutManager = layoutManager

        val adapter = TaskListAdapter(taskList,object :TaskDeleteInterface{
            override fun deleteTask(task: Task) {

                launch {
                    viewModel.deleteTask(task)
                    Toast.makeText(requireContext(),"${task.task} is Deleted",Toast.LENGTH_LONG).show()

                }

            }

        })
        binding.rvTaskList.adapter = adapter


    }


}