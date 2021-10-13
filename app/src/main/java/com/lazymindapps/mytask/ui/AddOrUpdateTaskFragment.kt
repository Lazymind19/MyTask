package com.lazymindapps.mytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lazymindapps.mytask.CustomToast.toasts
import com.lazymindapps.mytask.MainActivity
import com.lazymindapps.mytask.R
import com.lazymindapps.mytask.databinding.FragmentAddOrUpdateTaskBinding
import com.lazymindapps.mytask.db.model.Task
import com.lazymindapps.mytask.util.Constants
import com.lazymindapps.mytask.viewModel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.w3c.dom.ls.LSException
import kotlin.coroutines.CoroutineContext


class AddOrUpdateTaskFragment : Fragment(),CoroutineScope {
    lateinit var binding: FragmentAddOrUpdateTaskBinding
    lateinit var viewModel:TaskViewModel
    private val job = Job()

    private val args:AddOrUpdateTaskFragmentArgs by navArgs()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddOrUpdateTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.btnDone.setOnClickListener {
            saveTask()
        }

        binding.btnUpdate.setOnClickListener {
            updateTask()
        }

        if (args.fragmentFrom ==Constants.SAVE_TASK_FRAGMENT){
            binding.btnDone.visibility = View.VISIBLE
            binding.btnUpdate.visibility = View.GONE

        }
        if(args.fragmentFrom == Constants.EDIT_TASK_FRAGMENT){
            binding.btnUpdate.visibility = View.VISIBLE
            binding.btnDone.visibility = View.GONE

            binding.etTitle.editText?.setText(args.taskTitle)
            binding.etDescription.editText?.setText(args.taskDescription)


        }


    }



    private fun saveTask(){
        val title:String = binding.etTitle.editText?.text.toString()
        val description:String = binding.etDescription.editText?.text.toString()
        if (title!="") {

            val task = Task(task = title, description = description, date = 0)
            launch {
                viewModel.insertTask(task)
                val action = AddOrUpdateTaskFragmentDirections.actionAddOrUpdateTaskFragmentToTaskListFragment()
                findNavController().navigate(action)
                toasts(requireContext(),"Task saved")

            }
        }
        else{
            toasts(requireContext(),"Title is empty")

        }

    }

    private fun updateTask(){
        val title:String = binding.etTitle.editText?.text.toString()
        val description:String = binding.etDescription.editText?.text.toString()
        if (title!="") {

            launch {
                args.sn?.let { viewModel.updateTask(title,description, it.toInt()) }
                val action = AddOrUpdateTaskFragmentDirections.actionAddOrUpdateTaskFragmentToTaskListFragment()
                findNavController().navigate(action)
                toasts(requireContext(),"Task updated")

            }
        }
        else{
            toasts(requireContext(),"Title is empty")

        }

    }




}