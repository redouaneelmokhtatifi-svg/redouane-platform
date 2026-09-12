package com.redouane.educational.platform.data.repository

import com.redouane.educational.platform.data.database.EducationLevelDao
import com.redouane.educational.platform.data.database.GradeDao
import com.redouane.educational.platform.data.database.StreamDao
import com.redouane.educational.platform.data.database.SubjectDao
import com.redouane.educational.platform.data.models.EducationLevel
import com.redouane.educational.platform.data.models.Grade
import com.redouane.educational.platform.data.models.Stream
import com.redouane.educational.platform.data.models.Subject
import kotlinx.coroutines.flow.Flow

class EducationRepository(
    private val educationLevelDao: EducationLevelDao,
    private val gradeDao: GradeDao,
    private val streamDao: StreamDao,
    private val subjectDao: SubjectDao
) {
    fun getAllEducationLevels(): Flow<List<EducationLevel>> = educationLevelDao.getAllLevels()

    fun getEducationLevelById(id: String): Flow<EducationLevel?> = educationLevelDao.getLevelById(id)

    suspend fun insertEducationLevels(levels: List<EducationLevel>) {
        educationLevelDao.insertLevels(levels)
    }

    fun getGradesByLevel(levelId: String): Flow<List<Grade>> = gradeDao.getGradesByLevel(levelId)

    suspend fun insertGrades(grades: List<Grade>) {
        gradeDao.insertGrades(grades)
    }

    fun getStreamsByGrade(gradeId: String): Flow<List<Stream>> = streamDao.getStreamsByGrade(gradeId)

    suspend fun insertStreams(streams: List<Stream>) {
        streamDao.insertStreams(streams)
    }

    fun getSubjectsByStream(streamId: String): Flow<List<Subject>> = subjectDao.getSubjectsByStream(streamId)

    suspend fun insertSubjects(subjects: List<Subject>) {
        subjectDao.insertSubjects(subjects)
    }
}
