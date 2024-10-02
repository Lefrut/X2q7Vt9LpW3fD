package com.effective.vacancies.di

import com.effective.general.repository.VacanciesRepository
import com.effective.vacancies.repository.VacanciesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindVacanciesRepository(
        vacanciesRepositoryImpl: VacanciesRepositoryImpl
    ): VacanciesRepository

}