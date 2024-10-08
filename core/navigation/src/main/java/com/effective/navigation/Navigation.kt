package com.effective.navigation

import androidx.fragment.app.Fragment

interface Navigation {

    fun navigateToBottomMenu()

    fun navigateToVacancy(id: String)
    fun navigateToHome()
    fun navigateToFavorite()
    fun navigateToResponses()
    fun navigateToProfile()
    fun navigateToMessages()

    fun navigateToResponse(vacancyTitle: String)

    fun switchFragmentByTag(targetFragmentTag: String)

    fun prepareBottomNavigation()
    fun navigateBack()

}

val Fragment.navController get() = (requireActivity() as Navigation)