package com.effective.vacancies.di

import android.content.Context
import androidx.room.Room
import com.effective.vacancies.room.VacancyDatabase
import com.effective.vacancies.room.dao.VacanciesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
data object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        appContext: Context
    ): VacancyDatabase {
        return Room.databaseBuilder(
            appContext,
            VacancyDatabase::class.java,
            "vacancy_database.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideVacancyDao(database: VacancyDatabase): VacanciesDao {
        return database.vacanciesDao()
    }
}
