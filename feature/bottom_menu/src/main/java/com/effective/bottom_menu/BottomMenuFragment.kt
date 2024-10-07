package com.effective.bottom_menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.effective.bottom_menu.databinding.FragmentBottomMenuBinding
import com.effective.navigation.navController
import com.effective.ui.flow.collectWithLifecycle
import com.effective.ui.res.getAttrColor
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import com.effective.resources.R as Res
import com.google.android.material.R as MaterialRes

@AndroidEntryPoint
class BottomMenuFragment : Fragment(R.layout.fragment_bottom_menu) {

    private lateinit var binding: FragmentBottomMenuBinding
    private val viewModel by viewModels<BottomMenuViewModel>()

    @SuppressLint("ResourceType", "Recycle")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomMenuBinding.bind(view)

        val bottomNavigation = binding.bottomNavigation
        val badge = bottomNavigation.getOrCreateBadge(R.id.favorite).apply {
            backgroundColor = requireContext().getAttrColor(MaterialRes.attr.colorError)
            badgeTextColor = requireContext().getAttrColor(MaterialRes.attr.colorSurface)

            val typedArray =
                requireContext().obtainStyledAttributes(intArrayOf(Res.attr.textAppearanceNumber))
            val textAppearanceResId = typedArray.getResourceId(0, -1)
            setTextAppearance(textAppearanceResId)
            typedArray.recycle()
        }

        bottomNavigation.listenBottomNavigation()
        navController.prepareBottomNavigation()

        viewModel.countFavorites.collectWithLifecycle(viewLifecycleOwner) { count ->
            if (count <= 0) badge.isVisible = false
            else {
                badge.isVisible = true
                badge.number = count
            }
        }

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