package com.effective.navigation

import androidx.fragment.app.Fragment

interface BottomNavigation {

    fun navigateToHome()
    fun navigateToFavorite()
    fun navigateToResponses()
    fun navigateToProfile()
    fun navigateToMessages()

    fun<T: Fragment> switchFragment(targetFragment: T)

    fun prepareBottomNavigation()

}

val Fragment.bottomNavController get() = (requireActivity() as BottomNavigation)