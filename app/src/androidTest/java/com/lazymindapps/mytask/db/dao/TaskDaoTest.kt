package com.lazymindapps.mytask.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.lazymindapps.mytask.db.TaskDatabase
import com.lazymindapps.mytask.db.model.Task
import com.lazymindapps.mytask.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDaoTest {

    @get:Rule
    var instanceTaskExecutorRule =InstantTaskExecutorRule()

    private lateinit var database :TaskDatabase
    private lateinit var dao:TaskDao



    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
        TaskDatabase::class.java).allowMainThreadQueries().build()

        dao = database.getTaskDao()
    }

    @After
    fun destroyAll(){
        database.close()
    }

    @Test
    fun insertTaskTest() = runBlocking {
        val task = Task(1,"Cooking","For tonight",0)
        dao.insertTask(task)

        val allTasks = dao.getAllTask().getOrAwaitValue()
        assertThat(allTasks).contains(task)
    }

    @Test
    fun deleteTaskTest() = runBlocking {
        val task = Task(1,"Cooking","For tonight",0)
        dao.insertTask(task)
        dao.deleteTask(task)

        val allTasks = dao.getAllTask().getOrAwaitValue()
        assertThat(allTasks).doesNotContain(task)



    }
}