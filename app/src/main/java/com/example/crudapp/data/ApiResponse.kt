package com.example.crudapp.data

data class ApiResponse(
    val Search: List<Search>,
    val totalResults: String,
    val Response: String,
)
