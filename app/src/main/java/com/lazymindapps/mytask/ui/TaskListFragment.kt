package com.lazymindapps.mytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lazymindapps.mytask.R
import com.lazymindapps.mytask.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {
    lateinit var binding:FragmentTaskListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fbAddTask.setOnClickListener {
findNavController().navigate(R.id.action_taskListFragment_to_addOrUpdateTaskFragment)
        }

    }


}