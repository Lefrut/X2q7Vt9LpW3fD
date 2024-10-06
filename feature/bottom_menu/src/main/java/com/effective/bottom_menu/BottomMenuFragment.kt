package com.effective.bottom_menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.effective.bottom_menu.databinding.FragmentBottomMenuBinding
import com.effective.navigation.navController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomMenuFragment : Fragment(R.layout.fragment_bottom_menu) {

    private lateinit var binding: FragmentBottomMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomMenuBinding.bind(view)

        val bottomNavigation = binding.bottomNavigation

        bottomNavigation.listenBottomNavigation()
        navController.prepareBottomNavigation()
    }

    private fun BottomNavigationView.listenBottomNavigation() = setOnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> { navController.navigateToHome() }

            R.id.profile -> {

            }

            R.id.favorite -> { navController.navigateToFavorite() }

            R.id.messages -> {

            }

            R.id.responses -> {

            }

            else -> {
                return@setOnItemSelectedListener false
            }
        }
        return@setOnItemSelectedListener true
    }

}