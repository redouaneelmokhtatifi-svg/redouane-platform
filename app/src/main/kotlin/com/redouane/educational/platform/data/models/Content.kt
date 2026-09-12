package com.redouane.educational.platform.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey val id: String,
    val subjectId: String,
    val unitId: String? = null,
    val title: String,
    val titleAr: String,
    val content: String,
    val contentAr: String,
    val objectives: List<String>? = null,
    val pdfUrl: String? = null,
    val videoUrl: String? = null,
    val imageUrl: String? = null,
    val source: String, // المصدر: وزارة التربية، TelmidTICE، AlloSchool
    val sourceUrl: String? = null, // الرابط الأصلي
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val isOfflineAvailable: Boolean = false,
    val lastAccessedAt: Date? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey val id: String,
    val subjectId: String,
    val lessonId: String? = null,
    val title: String,
    val titleAr: String,
    val description: String,
    val descriptionAr: String,
    val difficulty: Difficulty,
    val exerciseContent: String,
    val solution: String? = null,
    val pdfUrl: String? = null,
    val source: String,
    val sourceUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val isOfflineAvailable: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

@Entity(tableName = "exams")
data class Exam(
    @PrimaryKey val id: String,
    val subjectId: String,
    val examYear: Int,
    val examSession: String, // "عادية" أو "استثنائية"
    val title: String,
    val titleAr: String,
    val examDate: Date? = null,
    val pdfUrl: String? = null,
    val solutionUrl: String? = null,
    val source: String,
    val sourceUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val isOfflineAvailable: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

@Entity(tableName = "assignments")
data class Assignment(
    @PrimaryKey val id: String,
    val subjectId: String,
    val title: String,
    val titleAr: String,
    val description: String,
    val descriptionAr: String,
    val dueDate: Date? = null,
    val pdfUrl: String? = null,
    val solutionUrl: String? = null,
    val source: String,
    val sourceUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val isOfflineAvailable: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

@Entity(tableName = "units")
data class Unit(
    @PrimaryKey val id: String,
    val subjectId: String,
    val name: String,
    val nameAr: String,
    val description: String? = null,
    val order: Int,
    val source: String,
    val createdAt: Date = Date()
)

enum class Difficulty {
    EASY,    // سهل
    MEDIUM,  // متوسط
    HARD     // صعب
}
