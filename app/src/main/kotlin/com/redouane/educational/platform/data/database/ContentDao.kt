package com.redouane.educational.platform.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.redouane.educational.platform.data.models.Assignment
import com.redouane.educational.platform.data.models.Exam
import com.redouane.educational.platform.data.models.Exercise
import com.redouane.educational.platform.data.models.Lesson
import com.redouane.educational.platform.data.models.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons WHERE subjectId = :subjectId ORDER BY title ASC")
    fun getLessonsBySubject(subjectId: String): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE unitId = :unitId ORDER BY title ASC")
    fun getLessonsByUnit(unitId: String): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE id = :id")
    fun getLessonById(id: String): Flow<Lesson?>

    @Query("SELECT * FROM lessons WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavoriteLessons(): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE isSaved = 1 ORDER BY title ASC")
    fun getSavedLessons(): Flow<List<Lesson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<Lesson>)

    @Update
    suspend fun updateLesson(lesson: Lesson)
}

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises WHERE subjectId = :subjectId ORDER BY title ASC")
    fun getExercisesBySubject(subjectId: String): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE lessonId = :lessonId ORDER BY title ASC")
    fun getExercisesByLesson(lessonId: String): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getExerciseById(id: String): Flow<Exercise?>

    @Query("SELECT * FROM exercises WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavoriteExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE isSaved = 1 ORDER BY title ASC")
    fun getSavedExercises(): Flow<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<Exercise>)

    @Update
    suspend fun updateExercise(exercise: Exercise)
}

@Dao
interface ExamDao {
    @Query("SELECT * FROM exams WHERE subjectId = :subjectId ORDER BY title ASC")
    fun getExamsBySubject(subjectId: String): Flow<List<Exam>>

    @Query("SELECT * FROM exams WHERE id = :id")
    fun getExamById(id: String): Flow<Exam?>

    @Query("SELECT * FROM exams WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavoriteExams(): Flow<List<Exam>>

    @Query("SELECT * FROM exams WHERE isSaved = 1 ORDER BY title ASC")
    fun getSavedExams(): Flow<List<Exam>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: Exam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExams(exams: List<Exam>)

    @Update
    suspend fun updateExam(exam: Exam)
}

@Dao
interface AssignmentDao {
    @Query("SELECT * FROM assignments WHERE subjectId = :subjectId ORDER BY title ASC")
    fun getAssignmentsBySubject(subjectId: String): Flow<List<Assignment>>

    @Query("SELECT * FROM assignments WHERE id = :id")
    fun getAssignmentById(id: String): Flow<Assignment?>

    @Query("SELECT * FROM assignments WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavoriteAssignments(): Flow<List<Assignment>>

    @Query("SELECT * FROM assignments WHERE isSaved = 1 ORDER BY title ASC")
    fun getSavedAssignments(): Flow<List<Assignment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignments(assignments: List<Assignment>)

    @Update
    suspend fun updateAssignment(assignment: Assignment)
}

@Dao
interface UnitDao {
    @Query("SELECT * FROM units WHERE subjectId = :subjectId ORDER BY `order` ASC")
    fun getUnitsBySubject(subjectId: String): Flow<List<Unit>>

    @Query("SELECT * FROM units WHERE id = :id")
    fun getUnitById(id: String): Flow<Unit?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(units: List<Unit>)

    @Update
    suspend fun updateUnit(unit: Unit)
}
