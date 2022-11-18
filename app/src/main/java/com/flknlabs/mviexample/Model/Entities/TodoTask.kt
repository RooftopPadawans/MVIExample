package com.flknlabs.mviexample.Model.Entities

data class TodoTask(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)