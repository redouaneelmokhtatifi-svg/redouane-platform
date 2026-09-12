package com.redouane.educational.platform.di

import android.content.Context
import com.redouane.educational.platform.data.database.RedouaneDatabase
import com.redouane.educational.platform.data.remote.RetrofitClient
import com.redouane.educational.platform.data.repository.ContentRepository
import com.redouane.educational.platform.data.repository.EducationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RedouaneDatabase = RedouaneDatabase.getInstance(context)
    
    @Singleton
    @Provides
    fun provideRetrofitClient(
        @ApplicationContext context: Context
    ): RetrofitClient = RetrofitClient(context)
    
    @Singleton
    @Provides
    fun provideEducationRepository(
        retrofitClient: RetrofitClient,
        database: RedouaneDatabase
    ): EducationRepository = EducationRepository(
        educationApi = retrofitClient.educationApi,
        educationLevelDao = database.educationLevelDao(),
        gradeDao = database.gradeDao(),
        streamDao = database.streamDao(),
        subjectDao = database.subjectDao()
    )
    
    @Singleton
    @Provides
    fun provideContentRepository(
        retrofitClient: RetrofitClient,
        database: RedouaneDatabase
    ): ContentRepository = ContentRepository(
        contentApi = retrofitClient.contentApi,
        lessonDao = database.lessonDao(),
        exerciseDao = database.exerciseDao(),
        examDao = database.examDao(),
        assignmentDao = database.assignmentDao()
    )
}
