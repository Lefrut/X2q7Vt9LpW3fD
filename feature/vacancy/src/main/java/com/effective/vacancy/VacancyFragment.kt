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
import com.effective.resources.R as Res

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

                        binding.vacancyCompany.text = vacancy.companyName

                        binding.vacancyLocation.text = getString(
                            Res.string.company_location,
                            vacancy.town,
                            vacancy.streat,
                            vacancy.house
                        )
                        binding.vacancyTitle.text = vacancy.title
                        binding.vacancyIncome.text = vacancy.salaryFull
                        binding.vacancyDesc.text = vacancy.desritpion
                        binding.vacancyExpirience.text =
                            getString(Res.string.expirience_vacancy, vacancy.experience)
                        binding.vacancyResponsibilities.text = vacancy.responsibilities
                        binding.vacancyShedules.text = vacancy.schedules.mapIndexed { index, s ->
                            if (index == 0) {
                                s.replaceFirstChar { it.uppercaseChar() }
                            } else s
                        }.joinToString()

                        binding.questionsList.adapter = QuestionsListAdapter(
                            requireContext(),
                            R.layout.question_item,
                            vacancy.questions
                        )


                        binding.watching.cardText.text =
                            resources.getQuantityString(
                                Res.plurals.loking_number,
                                vacancy.lookingNumber,
                                vacancy.lookingNumber
                            )
                        //binding.watching.cardImage.setImageResource(Res.drawable.eye_24)

                        binding.responded.cardText.text = resources.getQuantityString(
                            Res.plurals.alredy_responded,
                            vacancy.appiledNumber,
                            vacancy.appiledNumber
                        )
                        //binding.responded.cardImage.setImageResource(Res.drawable.profile_24)

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