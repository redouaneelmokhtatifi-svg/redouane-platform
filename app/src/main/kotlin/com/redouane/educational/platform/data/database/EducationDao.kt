package com.redouane.educational.platform.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.redouane.educational.platform.data.models.EducationLevel
import com.redouane.educational.platform.data.models.Grade
import com.redouane.educational.platform.data.models.Stream
import com.redouane.educational.platform.data.models.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationLevelDao {
    @Query("SELECT * FROM education_levels ORDER BY `order` ASC")
    fun getAllLevels(): Flow<List<EducationLevel>>

    @Query("SELECT * FROM education_levels WHERE id = :id")
    fun getLevelById(id: String): Flow<EducationLevel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevel(level: EducationLevel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevels(levels: List<EducationLevel>)

    @Update
    suspend fun updateLevel(level: EducationLevel)
}

@Dao
interface GradeDao {
    @Query("SELECT * FROM grades WHERE educationLevelId = :levelId ORDER BY `order` ASC")
    fun getGradesByLevel(levelId: String): Flow<List<Grade>>

    @Query("SELECT * FROM grades WHERE id = :id")
    fun getGradeById(id: String): Flow<Grade?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grade: Grade)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrades(grades: List<Grade>)

    @Update
    suspend fun updateGrade(grade: Grade)
}

@Dao
interface StreamDao {
    @Query("SELECT * FROM streams WHERE gradeId = :gradeId ORDER BY `order` ASC")
    fun getStreamsByGrade(gradeId: String): Flow<List<Stream>>

    @Query("SELECT * FROM streams WHERE id = :id")
    fun getStreamById(id: String): Flow<Stream?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStream(stream: Stream)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreams(streams: List<Stream>)

    @Update
    suspend fun updateStream(stream: Stream)
}

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects WHERE streamId = :streamId ORDER BY `order` ASC")
    fun getSubjectsByStream(streamId: String): Flow<List<Subject>>

    @Query("SELECT * FROM subjects WHERE id = :id")
    fun getSubjectById(id: String): Flow<Subject?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjects: List<Subject>)

    @Update
    suspend fun updateSubject(subject: Subject)
}
