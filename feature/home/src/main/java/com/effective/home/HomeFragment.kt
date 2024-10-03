package com.effective.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.effective.home.databinding.FragmentHomeBinding
import com.effective.home.model.HomeUiState
import com.effective.home.ui.adapter_delegates.buttonAdapterDelegate
import com.effective.home.ui.adapter_delegates.fastFilterAdapterDelegate
import com.effective.home.ui.adapter_delegates.fastFilterListAdapterDelegate
import com.effective.home.ui.adapter_delegates.headlineTextAdapterDelegate
import com.effective.home.ui.adapter_delegates.vacanciesHeaderAdapterDelegate
import com.effective.ui.recycler.adapters.vacancyAdapterDelegate
import com.effective.home.ui.common.toHomeUi
import com.effective.home.ui.common.toVacanciesUi
import com.effective.ui.recycler.decorations.BottomMarginDecoration
import com.effective.ui.recycler.decorations.HeadlineDecoration
import com.effective.ui.flow.collectWithLifecycle
import com.effective.ui.metrics.dpRoundToPx
import com.effective.ui.recycler.RecylerItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.effective.resources.R as Res

@AndroidEntryPoint
class HomeFragment @Inject constructor() : Fragment(R.layout.fragment_home) {


    companion object {
        val Singleton = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter = createHomeAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val progressCircular = binding.progressCircular
        val textFieldLayout = binding.textField
        val mapButton = binding.floatingMapButton

        createRecyclerView()

        homeViewModel.screenState.collectWithLifecycle(viewLifecycleOwner) { uiState ->
            when (uiState) {
                HomeUiState.Home -> homeViewModel.vacanciesAndFastFilters.collect { vacanciesAndFastFilters ->
                    progressCircular.visibility = View.GONE
                    mapButton.visibility = View.GONE
                    val homeItems = vacanciesAndFastFilters.toHomeUi(requireContext())
                    updateRecycelerView(homeItems)
                    textFieldLayout.setStartIconDrawable(Res.drawable.search_24)
                    textFieldLayout.setHint(Res.string.search_vacancies_hint)
                }

                HomeUiState.Vacancies ->{
                    binding.homeRecyclerView.scrollToPosition(0)
                    homeViewModel.vacanciesAndFastFilters.collect { vacanciesAndFastFilters ->
                        mapButton.visibility = View.VISIBLE
                        progressCircular.visibility = View.GONE
                        val recylerItems = vacanciesAndFastFilters.toVacanciesUi(requireContext())
                        updateRecycelerView(recylerItems)
                        textFieldLayout.setStartIconDrawable(Res.drawable.arrow_back_24)
                        textFieldLayout.setStartIconOnClickListener {
                            homeViewModel.goToHome()
                        }
                        textFieldLayout.setHint(Res.string.search_vacancies_hint_v2)
                    }
                }

                else -> {
                    mapButton.visibility = View.GONE
                    progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun createHomeAdapter(): ListDelegationAdapter<List<RecylerItem>> {
        return ListDelegationAdapter(
            fastFilterListAdapterDelegate(
                fastFilterAdapterDelegate()
            ),
            headlineTextAdapterDelegate(),
            vacancyAdapterDelegate(
                onFavoriteChange = { vacancyItem ->
                    homeViewModel.changeVacancyFavorite(vacancyItem)
                },
                onButtonClick = {

                },
                onCardClick = {

                }
            ),
            buttonAdapterDelegate { homeViewModel.goToVacancies() },
            vacanciesHeaderAdapterDelegate()
        )
    }

    private fun createRecyclerView() {
        val homeRecyclerView = binding.homeRecyclerView
        homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        homeRecyclerView.adapter = homeAdapter


        homeRecyclerView.apply {
            addItemDecoration(BottomMarginDecoration(16.dpRoundToPx(requireContext())))
            addItemDecoration(HeadlineDecoration(16.dpRoundToPx(requireContext())))
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecycelerView(homeItems: List<RecylerItem>) {
        homeAdapter.items = homeItems
        homeAdapter.notifyDataSetChanged()
    }
}