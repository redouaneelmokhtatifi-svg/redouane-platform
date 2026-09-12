package com.redouane.educational.platform.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val educationLevelId: String? = null,
    val gradeId: String? = null,
    val streamId: String? = null,
    val profileImageUrl: String? = null,
    val isDarkMode: Boolean = false,
    val language: String = "ar",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
