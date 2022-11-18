package com.flknlabs.mviexample.Intent

import com.flknlabs.mviexample.Model.Entities.TodoTask

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class LoadTasks(val todoTasks: List<TodoTask>): MainState()
    data class Error(val error: String?): MainState()
}