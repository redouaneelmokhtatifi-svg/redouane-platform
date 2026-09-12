package com.redouane.educational.platform.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey val id: String,
    val subjectId: String,
    val unitId: String,
    val title: String,
    val titleAr: String,
    val description: String,
    val descriptionAr: String,
    val content: String? = null, // محتوى HTML أو نص
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val documentUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
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
    val documentUrl: String? = null,
    val solutionUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

@Entity(tableName = "exams")
data class Exam(
    @PrimaryKey val id: String,
    val subjectId: String,
    val title: String,
    val titleAr: String,
    val description: String,
    val descriptionAr: String,
    val examDate: Date? = null,
    val documentUrl: String? = null,
    val solutionUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
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
    val documentUrl: String? = null,
    val solutionUrl: String? = null,
    val isFavorite: Boolean = false,
    val isSaved: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

@Entity(tableName = "units")
data class Unit(
    @PrimaryKey val id: String,
    val subjectId: String,
    val name: String,
    val nameAr: String,
    val order: Int,
    val createdAt: Date = Date()
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}
