package com.flknlabs.mviexample.Intent

sealed class MainIntent {
    class FetchTodoTasks(val dataString: String): MainIntent()
}