package com.rawa.notes.db

import androidx.room.TypeConverter
import timber.log.Timber
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class TimeConverter {
    @TypeConverter
    fun fromOffsetDateTime(odt: OffsetDateTime?): String? {
        return odt?.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    @TypeConverter
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        value ?: return null
        return OffsetDateTime.parse(value)

    }
}