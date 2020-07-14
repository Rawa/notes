package com.rawa.notes.domain

data class Note(
    val title: String,
    val text: String,
    val id: Long = 0
)
