package com.redouane.educational.platform.data.repository

import com.redouane.educational.platform.data.database.*
import com.redouane.educational.platform.data.models.*
import com.redouane.educational.platform.data.remote.ContentApi
import com.redouane.educational.platform.data.remote.EducationApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/**
 * مستودع الهيكل التعليمي
 * يقوم بجلب البيانات من الشبكة والتخزين المحلي
 */
class EducationRepository(
    private val educationApi: EducationApi,
    private val educationLevelDao: EducationLevelDao,
    private val gradeDao: GradeDao,
    private val streamDao: StreamDao,
    private val subjectDao: SubjectDao
) {
    
    fun getAllEducationLevels(): Flow<List<EducationLevel>> = flow {
        try {
            // جرب الحصول على البيانات من الشبكة
            val levels = educationApi.getEducationLevels()
            educationLevelDao.insertLevels(levels)
            emit(levels)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب المستويات التعليمية")
            // إذا فشل، جرب من قاعدة البيانات المحلية
            emit(emptyList())
        }
    }
    
    fun getEducationLevelById(id: String): Flow<EducationLevel?> = educationLevelDao.getLevelById(id)
    
    suspend fun insertEducationLevels(levels: List<EducationLevel>) {
        educationLevelDao.insertLevels(levels)
    }
    
    fun getGradesByLevel(levelId: String): Flow<List<Grade>> = flow {
        try {
            val grades = educationApi.getGradesByLevel(levelId)
            gradeDao.insertGrades(grades)
            emit(grades)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب السنوات الدراسية")
            emit(emptyList())
        }
    }
    
    suspend fun insertGrades(grades: List<Grade>) {
        gradeDao.insertGrades(grades)
    }
    
    fun getStreamsByGrade(gradeId: String): Flow<List<Stream>> = flow {
        try {
            val streams = educationApi.getStreamsByGrade(gradeId)
            streamDao.insertStreams(streams)
            emit(streams)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب الشعب")
            emit(emptyList())
        }
    }
    
    suspend fun insertStreams(streams: List<Stream>) {
        streamDao.insertStreams(streams)
    }
    
    fun getSubjectsByStream(streamId: String): Flow<List<Subject>> = flow {
        try {
            val subjects = educationApi.getSubjectsByStream(streamId)
            subjectDao.insertSubjects(subjects)
            emit(subjects)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب المواد")
            emit(emptyList())
        }
    }
    
    suspend fun insertSubjects(subjects: List<Subject>) {
        subjectDao.insertSubjects(subjects)
    }
}

/**
 * مستودع المحتوى التعليمي
 * يدير الدروس والتمارين والامتحانات والفروض
 */
class ContentRepository(
    private val contentApi: ContentApi,
    private val lessonDao: LessonDao,
    private val exerciseDao: ExerciseDao,
    private val examDao: ExamDao,
    private val assignmentDao: AssignmentDao
) {
    
    // الدروس
    fun getLessonsBySubject(subjectId: String): Flow<List<Lesson>> = flow {
        try {
            val lessons = contentApi.getLessonsBySubject(subjectId)
            lessonDao.insertLessons(lessons)
            emit(lessons)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب الدروس للمادة: $subjectId")
            emit(emptyList())
        }
    }
    
    fun getLessonById(id: String): Flow<Lesson?> = lessonDao.getLessonById(id)
    
    fun getFavoriteLessons(): Flow<List<Lesson>> = lessonDao.getFavoriteLessons()
    
    fun getSavedLessons(): Flow<List<Lesson>> = lessonDao.getSavedLessons()
    
    suspend fun updateLesson(lesson: Lesson) = lessonDao.updateLesson(lesson)
    
    suspend fun insertLessons(lessons: List<Lesson>) = lessonDao.insertLessons(lessons)
    
    // التمارين
    fun getExercisesBySubject(subjectId: String): Flow<List<Exercise>> = flow {
        try {
            val exercises = contentApi.getExercisesBySubject(subjectId)
            exerciseDao.insertExercises(exercises)
            emit(exercises)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب التمارين للمادة: $subjectId")
            emit(emptyList())
        }
    }
    
    fun getFavoriteExercises(): Flow<List<Exercise>> = exerciseDao.getFavoriteExercises()
    
    fun getSavedExercises(): Flow<List<Exercise>> = exerciseDao.getSavedExercises()
    
    suspend fun updateExercise(exercise: Exercise) = exerciseDao.updateExercise(exercise)
    
    suspend fun insertExercises(exercises: List<Exercise>) = exerciseDao.insertExercises(exercises)
    
    // الامتحانات
    fun getExamsBySubject(subjectId: String): Flow<List<Exam>> = flow {
        try {
            val exams = contentApi.getExamsBySubject(subjectId)
            examDao.insertExams(exams)
            emit(exams)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب الامتحانات للمادة: $subjectId")
            emit(emptyList())
        }
    }
    
    fun getFavoriteExams(): Flow<List<Exam>> = examDao.getFavoriteExams()
    
    fun getSavedExams(): Flow<List<Exam>> = examDao.getSavedExams()
    
    suspend fun updateExam(exam: Exam) = examDao.updateExam(exam)
    
    suspend fun insertExams(exams: List<Exam>) = examDao.insertExams(exams)
    
    // الفروض
    fun getAssignmentsBySubject(subjectId: String): Flow<List<Assignment>> = flow {
        try {
            val assignments = contentApi.getAssignmentsBySubject(subjectId)
            assignmentDao.insertAssignments(assignments)
            emit(assignments)
        } catch (e: Exception) {
            Timber.e(e, "خطأ في جلب الفروض للمادة: $subjectId")
            emit(emptyList())
        }
    }
    
    fun getFavoriteAssignments(): Flow<List<Assignment>> = assignmentDao.getFavoriteAssignments()
    
    fun getSavedAssignments(): Flow<List<Assignment>> = assignmentDao.getSavedAssignments()
    
    suspend fun updateAssignment(assignment: Assignment) = assignmentDao.updateAssignment(assignment)
    
    suspend fun insertAssignments(assignments: List<Assignment>) = assignmentDao.insertAssignments(assignments)
}
