package com.redouane.educational.platform.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "cached_content")
data class CachedContent(
    @PrimaryKey val id: String,
    val contentId: String,
    val contentType: ContentType, // LESSON, EXERCISE, EXAM, ASSIGNMENT
    val title: String,
    val localPath: String,
    val size: Long,
    val cachedAt: Date = Date(),
    val accessedAt: Date = Date()
)

enum class ContentType {
    LESSON,
    EXERCISE,
    EXAM,
    ASSIGNMENT
}
