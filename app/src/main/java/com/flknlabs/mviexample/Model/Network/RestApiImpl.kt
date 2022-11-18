package com.flknlabs.mviexample.Model.Network

import com.flknlabs.mviexample.Model.Entities.TodoTask

class RestApiImpl(private val restApiService: RestApiService): RestAPI {

    override suspend fun getTodoTasks(): List<TodoTask> {
        return restApiService.listTodo()
    }
}