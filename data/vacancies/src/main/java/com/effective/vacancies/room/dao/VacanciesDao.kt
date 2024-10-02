package com.effective.vacancies.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.effective.vacancies.room.model.FavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface VacanciesDao {

    @Query("SELECT * FROM favorite_vacancies WHERE is_favorite = 1")
    fun getAllVacancies(): Flow<List<FavoriteVacancyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: FavoriteVacancyEntity)

    @Transaction
    suspend fun deleteVacancy(vacancy: FavoriteVacancyEntity) {
        deleteVacancyById(vacancy.id)
    }

    @Query("DELETE FROM favorite_vacancies WHERE id = :vacancyId")
    suspend fun deleteVacancyById(vacancyId: String)

}