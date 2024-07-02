package com.example.trackbuss.states

sealed class SearchEvent {
    data class QueryChanged(val query: String) : SearchEvent()
    object Submit: SearchEvent()
}