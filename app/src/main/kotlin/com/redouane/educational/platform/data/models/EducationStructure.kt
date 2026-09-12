package com.redouane.educational.platform.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// المصدر: وزارة التربية الوطنية والتعليم الأولي والرياضة
// الهيكل التنظيمي للتعليم المغربي

@Entity(tableName = "education_levels")
data class EducationLevel(
    @PrimaryKey val id: String,
    val nameAr: String,
    val nameEn: String,
    val icon: String,
    val order: Int,
    val source: String = "وزارة التربية الوطنية"
)

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey val id: String,
    val educationLevelId: String,
    val nameAr: String,
    val nameEn: String,
    val order: Int,
    val source: String = "وزارة التربية الوطنية"
)

@Entity(tableName = "streams")
data class Stream(
    @PrimaryKey val id: String,
    val gradeId: String,
    val nameAr: String,
    val nameEn: String,
    val type: StreamType,
    val order: Int,
    val source: String = "وزارة التربية الوطنية"
)

enum class StreamType {
    GENERAL,      // عام (ابتدائي)
    ARTS,         // آداب وعلوم إنسانية
    SCIENCE,      // علوم رياضية
    SCIENCE_LIFE, // علوم الحياة والأرض
    VOCATIONAL    // تقني وتكنولوجي
}

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey val id: String,
    val streamId: String,
    val nameAr: String,
    val nameEn: String,
    val description: String? = null,
    val icon: String? = null,
    val color: String,
    val order: Int,
    val source: String = "وزارة التربية الوطنية"
)
