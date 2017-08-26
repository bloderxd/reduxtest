package com.vitorprado.reduxtest

data class AppState(
        val user: String,
        val events: List<String>
)