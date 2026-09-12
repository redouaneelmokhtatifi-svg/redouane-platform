package com.redouane.educational.platform.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redouane.educational.platform.data.models.Assignment
import com.redouane.educational.platform.data.models.CachedContent
import com.redouane.educational.platform.data.models.EducationLevel
import com.redouane.educational.platform.data.models.Exam
import com.redouane.educational.platform.data.models.Exercise
import com.redouane.educational.platform.data.models.Grade
import com.redouane.educational.platform.data.models.Lesson
import com.redouane.educational.platform.data.models.Stream
import com.redouane.educational.platform.data.models.Subject
import com.redouane.educational.platform.data.models.Unit
import com.redouane.educational.platform.data.models.User

@Database(
    entities = [
        EducationLevel::class,
        Grade::class,
        Stream::class,
        Subject::class,
        Unit::class,
        Lesson::class,
        Exercise::class,
        Exam::class,
        Assignment::class,
        User::class,
        CachedContent::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RedouaneDatabase : RoomDatabase() {
    abstract fun educationLevelDao(): EducationLevelDao
    abstract fun gradeDao(): GradeDao
    abstract fun streamDao(): StreamDao
    abstract fun subjectDao(): SubjectDao
    abstract fun unitDao(): UnitDao
    abstract fun lessonDao(): LessonDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun examDao(): ExamDao
    abstract fun assignmentDao(): AssignmentDao
    abstract fun userDao(): UserDao
    abstract fun cachedContentDao(): CachedContentDao

    companion object {
        @Volatile
        private var instance: RedouaneDatabase? = null

        fun getInstance(context: Context): RedouaneDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    RedouaneDatabase::class.java,
                    "redouane_educational.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
