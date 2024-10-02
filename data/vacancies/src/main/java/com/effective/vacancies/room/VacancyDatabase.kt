package com.effective.vacancies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.effective.vacancies.room.dao.VacanciesDao
import com.effective.vacancies.room.model.FavoriteVacancyEntity

@Database(entities = [FavoriteVacancyEntity::class], version = 1)
@TypeConverters(DatabaseConverters::class)
abstract class VacancyDatabase : RoomDatabase() {

    abstract fun vacanciesDao(): VacanciesDao


}