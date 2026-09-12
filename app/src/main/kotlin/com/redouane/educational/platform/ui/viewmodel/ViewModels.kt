package com.redouane.educational.platform.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redouane.educational.platform.data.models.*
import com.redouane.educational.platform.data.repository.EducationRepository
import com.redouane.educational.platform.data.repository.ContentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

data class EducationUiState(
    val educationLevels: List<EducationLevel> = emptyList(),
    val selectedLevel: EducationLevel? = null,
    val grades: List<Grade> = emptyList(),
    val selectedGrade: Grade? = null,
    val streams: List<Stream> = emptyList(),
    val selectedStream: Stream? = null,
    val subjects: List<Subject> = emptyList(),
    val selectedSubject: Subject? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class EducationViewModel(
    private val educationRepository: EducationRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(EducationUiState())
    val uiState: StateFlow<EducationUiState> = _uiState.asStateFlow()
    
    init {
        loadEducationLevels()
    }
    
    private fun loadEducationLevels() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                educationRepository.getAllEducationLevels().collect { levels ->
                    _uiState.value = _uiState.value.copy(
                        educationLevels = levels,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل المستويات التعليمية")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل المستويات التعليمية",
                    isLoading = false
                )
            }
        }
    }
    
    fun selectLevel(level: EducationLevel) {
        _uiState.value = _uiState.value.copy(
            selectedLevel = level,
            grades = emptyList(),
            selectedGrade = null,
            streams = emptyList(),
            selectedStream = null,
            subjects = emptyList(),
            selectedSubject = null
        )
        loadGradesByLevel(level.id)
    }
    
    private fun loadGradesByLevel(levelId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                educationRepository.getGradesByLevel(levelId).collect { grades ->
                    _uiState.value = _uiState.value.copy(
                        grades = grades,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل السنوات الدراسية")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل السنوات الدراسية",
                    isLoading = false
                )
            }
        }
    }
    
    fun selectGrade(grade: Grade) {
        _uiState.value = _uiState.value.copy(
            selectedGrade = grade,
            streams = emptyList(),
            selectedStream = null,
            subjects = emptyList(),
            selectedSubject = null
        )
        loadStreamsByGrade(grade.id)
    }
    
    private fun loadStreamsByGrade(gradeId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                educationRepository.getStreamsByGrade(gradeId).collect { streams ->
                    _uiState.value = _uiState.value.copy(
                        streams = streams,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل الشعب")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل الشعب",
                    isLoading = false
                )
            }
        }
    }
    
    fun selectStream(stream: Stream) {
        _uiState.value = _uiState.value.copy(
            selectedStream = stream,
            subjects = emptyList(),
            selectedSubject = null
        )
        loadSubjectsByStream(stream.id)
    }
    
    private fun loadSubjectsByStream(streamId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                educationRepository.getSubjectsByStream(streamId).collect { subjects ->
                    _uiState.value = _uiState.value.copy(
                        subjects = subjects,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل المواد")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل المواد",
                    isLoading = false
                )
            }
        }
    }
    
    fun selectSubject(subject: Subject) {
        _uiState.value = _uiState.value.copy(selectedSubject = subject)
    }
}

data class ContentUiState(
    val lessons: List<Lesson> = emptyList(),
    val exercises: List<Exercise> = emptyList(),
    val exams: List<Exam> = emptyList(),
    val assignments: List<Assignment> = emptyList(),
    val favoriteLessons: List<Lesson> = emptyList(),
    val favoriteExercises: List<Exercise> = emptyList(),
    val favoriteExams: List<Exam> = emptyList(),
    val favoriteAssignments: List<Assignment> = emptyList(),
    val savedLessons: List<Lesson> = emptyList(),
    val savedExercises: List<Exercise> = emptyList(),
    val savedExams: List<Exam> = emptyList(),
    val savedAssignments: List<Assignment> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ContentViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ContentUiState())
    val uiState: StateFlow<ContentUiState> = _uiState.asStateFlow()
    
    fun loadLessonsBySubject(subjectId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                contentRepository.getLessonsBySubject(subjectId).collect { lessons ->
                    _uiState.value = _uiState.value.copy(
                        lessons = lessons,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل الدروس")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل الدروس",
                    isLoading = false
                )
            }
        }
    }
    
    fun loadExercisesBySubject(subjectId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                contentRepository.getExercisesBySubject(subjectId).collect { exercises ->
                    _uiState.value = _uiState.value.copy(
                        exercises = exercises,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل التمارين")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل التمارين",
                    isLoading = false
                )
            }
        }
    }
    
    fun loadExamsBySubject(subjectId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                contentRepository.getExamsBySubject(subjectId).collect { exams ->
                    _uiState.value = _uiState.value.copy(
                        exams = exams,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "خطأ في تحميل الامتحانات")
                _uiState.value = _uiState.value.copy(
                    errorMessage = "فشل تحميل الامتحانات",
                    isLoading = false
                )
            }
        }
    }
    
    fun toggleFavoriteLesson(lesson: Lesson) {
        viewModelScope.launch {
            contentRepository.updateLesson(lesson.copy(isFavorite = !lesson.isFavorite))
        }
    }
    
    fun toggleFavoriteExercise(exercise: Exercise) {
        viewModelScope.launch {
            contentRepository.updateExercise(exercise.copy(isFavorite = !exercise.isFavorite))
        }
    }
    
    fun toggleFavoriteExam(exam: Exam) {
        viewModelScope.launch {
            contentRepository.updateExam(exam.copy(isFavorite = !exam.isFavorite))
        }
    }
}
