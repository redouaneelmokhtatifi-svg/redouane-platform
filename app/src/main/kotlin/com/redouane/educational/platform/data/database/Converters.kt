package com.redouane.educational.platform.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.redouane.educational.platform.data.models.ContentType
import com.redouane.educational.platform.data.models.Difficulty
import com.redouane.educational.platform.data.models.StreamType
import java.util.Date

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromStreamType(value: StreamType?): String? = value?.name

    @TypeConverter
    fun toStreamType(value: String?): StreamType? = value?.let { StreamType.valueOf(it) }

    @TypeConverter
    fun fromDifficulty(value: Difficulty?): String? = value?.name

    @TypeConverter
    fun toDifficulty(value: String?): Difficulty? = value?.let { Difficulty.valueOf(it) }

    @TypeConverter
    fun fromContentType(value: ContentType?): String? = value?.name

    @TypeConverter
    fun toContentType(value: String?): ContentType? = value?.let { ContentType.valueOf(it) }
}
