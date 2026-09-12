package com.redouane.educational.platform.data.remote

import com.redouane.educational.platform.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * واجهات API للحصول على المحتوى التعليمي من المصادر الموثوقة
 * المصادر:
 * 1. وزارة التربية الوطنية (البيانات الرسمية)
 * 2. TelmidTICE (محتوى وزاري)
 * 3. AlloSchool (محتوى تعليمي إضافي)
 */
interface EducationApi {
    
    @GET("education/levels")
    suspend fun getEducationLevels(): List<EducationLevel>
    
    @GET("education/levels/{levelId}/grades")
    suspend fun getGradesByLevel(@Path("levelId") levelId: String): List<Grade>
    
    @GET("education/grades/{gradeId}/streams")
    suspend fun getStreamsByGrade(@Path("gradeId") gradeId: String): List<Stream>
    
    @GET("education/streams/{streamId}/subjects")
    suspend fun getSubjectsByStream(@Path("streamId") streamId: String): List<Subject>
}

interface ContentApi {
    
    @GET("subjects/{subjectId}/lessons")
    suspend fun getLessonsBySubject(
        @Path("subjectId") subjectId: String,
        @Query("source") source: String? = null
    ): List<Lesson>
    
    @GET("lessons/{lessonId}")
    suspend fun getLessonById(@Path("lessonId") lessonId: String): Lesson
    
    @GET("subjects/{subjectId}/exercises")
    suspend fun getExercisesBySubject(
        @Path("subjectId") subjectId: String,
        @Query("difficulty") difficulty: String? = null
    ): List<Exercise>
    
    @GET("subjects/{subjectId}/exams")
    suspend fun getExamsBySubject(
        @Path("subjectId") subjectId: String,
        @Query("year") year: Int? = null
    ): List<Exam>
    
    @GET("subjects/{subjectId}/assignments")
    suspend fun getAssignmentsBySubject(@Path("subjectId") subjectId: String): List<Assignment>
    
    @GET("search")
    suspend fun searchContent(
        @Query("q") query: String,
        @Query("type") type: String? = null,
        @Query("subject") subjectId: String? = null
    ): SearchResults
}

data class SearchResults(
    val lessons: List<Lesson> = emptyList(),
    val exercises: List<Exercise> = emptyList(),
    val exams: List<Exam> = emptyList(),
    val assignments: List<Assignment> = emptyList()
)
