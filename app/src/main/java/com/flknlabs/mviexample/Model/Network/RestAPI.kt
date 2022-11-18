package com.flknlabs.mviexample.Model.Network

import com.flknlabs.mviexample.Model.Entities.TodoTask

interface RestAPI {
    suspend fun getTodoTasks(): List<TodoTask>
}