package com.flknlabs.mviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.flknlabs.mviexample.Intent.MainIntent
import com.flknlabs.mviexample.Intent.MainState
import com.flknlabs.mviexample.Model.Entities.TodoTask
import com.flknlabs.mviexample.Model.Network.RestApiImpl
import com.flknlabs.mviexample.Model.Network.RetrofitBuilder
import com.flknlabs.mviexample.View.Adapters.MainAdapter
import com.flknlabs.mviexample.View.MainActivityViewModel
import com.flknlabs.mviexample.View.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels { MainViewModelFactory(
        RestApiImpl(
        RetrofitBuilder.apiService)
    ) }
    private var mainAdapter = MainAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupClicks()
        observeViewModel()
    }

    private fun setupClicks(){
        button_tasks.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.userIntent.send(MainIntent.FetchTodoTasks("new value"))
            }
        }
    }

    private fun setupUI(){
        recyclerview_tasks.layoutManager = LinearLayoutManager(this)

        recyclerview_tasks.run {
            adapter = mainAdapter
        }
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            mainActivityViewModel.mainState.collect {mainState->
                when(mainState){
                    is MainState.Idle -> progressbar.visibility = View.GONE
                    is MainState.Loading -> progressbar.visibility = View.VISIBLE

                    is MainState.LoadTasks -> {
                        progressbar.visibility = View.GONE
                        renderList(mainState.todoTasks)
                    }

                    is MainState.Error -> {
                        recyclerview_tasks.visibility = View.GONE
                        Toast.makeText(applicationContext, "Error: ${mainState.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderList(listTodoTask: List<TodoTask>){
        mainAdapter.setTodoTasks(listTodoTask)
        mainAdapter.notifyDataSetChanged()
    }
}