package com.lazymindapps.mytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.lazymindapps.mytask.databinding.ActivityMainBinding
import com.lazymindapps.mytask.db.TaskDatabase
import com.lazymindapps.mytask.repo.TaskRepo
import com.lazymindapps.mytask.viewModel.TaskViewModel
import com.lazymindapps.mytask.viewModel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController:NavController


     val viewModel: TaskViewModel by viewModels {
         TaskViewModelFactory((application as TaskApplication).repo)
     }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

      //  val dao:TaskDao  = TaskDatabase.createDatabase(applicationContext).getTaskDao()

        val taskRepo = TaskRepo(TaskDatabase.createDatabase(this))
        val taskViewModelFactory = TaskViewModelFactory(taskRepo)


       // viewModel = ViewModelProvider(this,taskViewModelFactory).get(TaskViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}