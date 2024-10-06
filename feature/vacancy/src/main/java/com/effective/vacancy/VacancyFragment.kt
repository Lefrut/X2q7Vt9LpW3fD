package com.effective.vacancy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.effective.navigation.navController
import com.effective.ui.common.Ui
import com.effective.ui.flow.collectWithLifecycle
import com.effective.vacancy.databinding.FragmentVacancyBinding
import com.effective.vacancy.ui.adapters.QuestionsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class VacancyFragment : Fragment(R.layout.fragment_vacancy) {

    private lateinit var binding: FragmentVacancyBinding
    private val viewModel by viewModels<VacancyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVacancyBinding.bind(view)

        val topAppBar = binding.topAppBar

        binding.topAppBar.setNavigationOnClickListener {
            navController.navigateBack()
        }


        viewModel.uiState.collectWithLifecycle(viewLifecycleOwner) { ui ->
            when (ui) {
                Ui.Success -> {
                    binding.appBarLayout.visibility = View.VISIBLE
                    binding.nestedScroll.visibility = View.VISIBLE
                    binding.progressCircular.visibility = View.GONE
                    viewModel.vacancyState.collectLatest { vacancy ->
                        topAppBar.menu.clear()
                        if (vacancy.isFavorite) topAppBar.inflateMenu(R.menu.active_top_menu)
                        else topAppBar.inflateMenu(R.menu.inactive_top_menu)

                        binding.vacancyTitle.text = vacancy.title
                        binding.vacancyIncome.text = vacancy.salaryFull
                        binding.vacancyDesc.text = vacancy.desritpion
                        binding.vacancyExpirience.text = vacancy.experiencePreview
                        binding.vacancyResponsibilities.text = vacancy.responsibilities
                        binding.vacancyShedules.text = vacancy.schedules.joinToString()

                        binding.questionsList.adapter = QuestionsListAdapter(
                            requireContext(),
                            R.layout.question_item,
                            vacancy.questions
                        )

                        binding.button.setOnClickListener {
                            navController.navigateToResponse(vacancy.title)
                        }
                    }
                }

                else -> {
                    binding.nestedScroll.visibility = View.INVISIBLE
                    binding.appBarLayout.visibility = View.INVISIBLE
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }

}