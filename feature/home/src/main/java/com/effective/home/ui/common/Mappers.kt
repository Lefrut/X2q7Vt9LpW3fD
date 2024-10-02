package com.effective.home.ui.common

import android.content.Context
import com.effective.general.model.VacanciesAndFastFilters
import com.effective.home.ui.adapter_delegates.ButtonItem
import com.effective.home.ui.adapter_delegates.FastFilterListItem
import com.effective.home.ui.adapter_delegates.HeaderTextItem
import com.effective.home.ui.adapter_delegates.VacanciesHeaderItem
import com.effective.home.ui.adapter_delegates.toUi
import com.effective.resources.R


fun VacanciesAndFastFilters.toHomeUi(context: Context): List<HomeItem> {
    val fastFiltersUi = fastFilters.map { it.toUi() }
    val vacanciesUi = vacancies.map { it.toUi(context) }

    val stillVacanciesCount = vacanciesUi.size - 3
    return buildList{
        add(FastFilterListItem(fastFiltersUi))
        add(HeaderTextItem(context.getString(R.string.vacancies_for_you)))
        addAll(vacanciesUi.take(3))
        add(
            ButtonItem(
                context.resources.getQuantityString(
                    R.plurals.still_vacancies,
                    stillVacanciesCount,
                    stillVacanciesCount
                )
            )
        )
    }
}

fun VacanciesAndFastFilters.toVacanciesUi(context: Context): List<HomeItem> {
    val vacanciesUi = vacancies.map { it.toUi(context) }
    return buildList {
        add(VacanciesHeaderItem(vacanciesUi.size))
        addAll(vacanciesUi)
    }
}