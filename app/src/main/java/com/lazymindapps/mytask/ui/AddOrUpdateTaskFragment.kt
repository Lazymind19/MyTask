package com.lazymindapps.mytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lazymindapps.mytask.R
import com.lazymindapps.mytask.databinding.FragmentAddOrUpdateTaskBinding


class AddOrUpdateTaskFragment : Fragment() {
    lateinit var binding: FragmentAddOrUpdateTaskBinding


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
        binding.btnDone.setOnClickListener {
            saveTask()
            findNavController().navigate(R.id.action_addOrUpdateTaskFragment_to_taskListFragment)
        }
    }

    private fun saveTask(){


    }





}