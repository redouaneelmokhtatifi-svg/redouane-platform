package com.redouane.educational.platform.data.repository

import com.redouane.educational.platform.data.database.LessonDao
import com.redouane.educational.platform.data.database.ExerciseDao
import com.redouane.educational.platform.data.database.ExamDao
import com.redouane.educational.platform.data.database.AssignmentDao
import com.redouane.educational.platform.data.models.Assignment
import com.redouane.educational.platform.data.models.Exam
import com.redouane.educational.platform.data.models.Exercise
import com.redouane.educational.platform.data.models.Lesson
import kotlinx.coroutines.flow.Flow

class ContentRepository(
    private val lessonDao: LessonDao,
    private val exerciseDao: ExerciseDao,
    private val examDao: ExamDao,
    private val assignmentDao: AssignmentDao
) {
    // Lessons
    fun getLessonsBySubject(subjectId: String): Flow<List<Lesson>> = lessonDao.getLessonsBySubject(subjectId)

    fun getFavoriteLessons(): Flow<List<Lesson>> = lessonDao.getFavoriteLessons()

    fun getSavedLessons(): Flow<List<Lesson>> = lessonDao.getSavedLessons()

    suspend fun updateLesson(lesson: Lesson) = lessonDao.updateLesson(lesson)

    suspend fun insertLessons(lessons: List<Lesson>) = lessonDao.insertLessons(lessons)

    // Exercises
    fun getExercisesBySubject(subjectId: String): Flow<List<Exercise>> = exerciseDao.getExercisesBySubject(subjectId)

    fun getFavoriteExercises(): Flow<List<Exercise>> = exerciseDao.getFavoriteExercises()

    fun getSavedExercises(): Flow<List<Exercise>> = exerciseDao.getSavedExercises()

    suspend fun updateExercise(exercise: Exercise) = exerciseDao.updateExercise(exercise)

    suspend fun insertExercises(exercises: List<Exercise>) = exerciseDao.insertExercises(exercises)

    // Exams
    fun getExamsBySubject(subjectId: String): Flow<List<Exam>> = examDao.getExamsBySubject(subjectId)

    fun getFavoriteExams(): Flow<List<Exam>> = examDao.getFavoriteExams()

    fun getSavedExams(): Flow<List<Exam>> = examDao.getSavedExams()

    suspend fun updateExam(exam: Exam) = examDao.updateExam(exam)

    suspend fun insertExams(exams: List<Exam>) = examDao.insertExams(exams)

    // Assignments
    fun getAssignmentsBySubject(subjectId: String): Flow<List<Assignment>> = assignmentDao.getAssignmentsBySubject(subjectId)

    fun getFavoriteAssignments(): Flow<List<Assignment>> = assignmentDao.getFavoriteAssignments()

    fun getSavedAssignments(): Flow<List<Assignment>> = assignmentDao.getSavedAssignments()

    suspend fun updateAssignment(assignment: Assignment) = assignmentDao.updateAssignment(assignment)

    suspend fun insertAssignments(assignments: List<Assignment>) = assignmentDao.insertAssignments(assignments)
}
