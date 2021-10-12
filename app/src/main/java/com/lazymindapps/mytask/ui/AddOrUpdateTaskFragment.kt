package com.lazymindapps.mytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lazymindapps.mytask.CustomToast.toasts
import com.lazymindapps.mytask.MainActivity
import com.lazymindapps.mytask.R
import com.lazymindapps.mytask.databinding.FragmentAddOrUpdateTaskBinding
import com.lazymindapps.mytask.db.model.Task
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


    }

    private fun saveTask(){
        val title:String = binding.etTitle.editText?.text.toString()
        val description:String = binding.etDescription.editText?.text.toString()
        if (title!="") {

            val task = Task(task = title, description = description, date = 0)
            launch {
                viewModel.insertTask(task)
                findNavController().navigate(R.id.action_addOrUpdateTaskFragment_to_taskListFragment)



            }
        }
        else{
            toasts(requireContext(),"Title is empty")

        }




    }




}