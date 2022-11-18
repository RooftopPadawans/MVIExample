package com.flknlabs.mviexample.Model.Network

import com.flknlabs.mviexample.Model.Entities.TodoTask
import retrofit2.http.GET

interface RestApiService {
    @GET("todos")
    suspend fun listTodo(): List<TodoTask>
}