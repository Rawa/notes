package com.rawa.notes.domain

import java.time.OffsetDateTime

data class Note(
    val title: String,
    val text: String,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val lastEditedAt: OffsetDateTime? = null,
    val archived: Boolean = false,
    val softDeleted: Boolean = false,
    val id: Long = 0
) {
    fun lastModified() = lastEditedAt ?: createdAt
}
