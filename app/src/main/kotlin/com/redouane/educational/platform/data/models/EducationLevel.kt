package com.redouane.educational.platform.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "education_levels")
data class EducationLevel(
    @PrimaryKey val id: String,
    val name: String, // ابتدائي, إعدادي, تأهيلي
    val nameAr: String,
    val icon: String,
    val order: Int
)

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey val id: String,
    val educationLevelId: String,
    val name: String, // السنة الأولى, السنة الثانية, إلخ
    val nameAr: String,
    val order: Int
)

@Entity(tableName = "streams")
data class Stream(
    @PrimaryKey val id: String,
    val gradeId: String,
    val name: String, // آداب, علوم, إلخ
    val nameAr: String,
    val type: StreamType,
    val order: Int
)

enum class StreamType {
    ARTS,        // آداب
    SCIENCE,     // علوم
    GENERAL      // عام
}

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey val id: String,
    val streamId: String,
    val name: String,
    val nameAr: String,
    val icon: String,
    val color: String,
    val order: Int
)
