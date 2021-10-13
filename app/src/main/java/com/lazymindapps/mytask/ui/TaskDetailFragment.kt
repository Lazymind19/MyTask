package com.lazymindapps.mytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.lazymindapps.mytask.R
import com.lazymindapps.mytask.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {
    lateinit var binding:FragmentTaskDetailBinding

    private val args:TaskDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = args.taskTitle
        binding.tvDescription.text = args.taskDescription
    }

}