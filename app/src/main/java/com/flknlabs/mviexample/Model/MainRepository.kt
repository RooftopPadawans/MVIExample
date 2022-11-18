package com.flknlabs.mviexample.Model

import com.flknlabs.mviexample.Model.Network.RestAPI

class MainRepository(private val restApi: RestAPI) {

    suspend fun getTodoTasks() = restApi.getTodoTasks()
}