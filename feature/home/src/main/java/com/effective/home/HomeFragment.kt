package com.effective.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.effective.home.databinding.FragmentHomeBinding
import com.effective.home.ui.adapter_delegates.buttonAdapterDelegate
import com.effective.home.ui.adapter_delegates.fastFilterAdapterDelegate
import com.effective.home.ui.adapter_delegates.fastFilterListAdapterDelegate
import com.effective.home.ui.adapter_delegates.headlineTextAdapterDelegate
import com.effective.home.ui.adapter_delegates.vacancyAdapterDelegate
import com.effective.home.ui.common.HomeItem
import com.effective.home.ui.common.toUi
import com.effective.home.ui.decorations.BottomMarginDecoration
import com.effective.home.ui.decorations.HeadlineDecoration
import com.effective.ui.common.ScreenState
import com.effective.ui.flow.collectWithLifecycle
import com.effective.ui.metrics.dpRoundToPx
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

        createRecyclerView()

        homeViewModel.screenState.collectWithLifecycle(viewLifecycleOwner) { screenState ->
            when (screenState) {
                ScreenState.Ready -> homeViewModel.vacanciesAndFastFilters.collect { vacanciesAndFastFilters ->
                    val homeItems = vacanciesAndFastFilters.toUi(requireContext())
                    progressCircular.visibility = View.GONE
                    updateRecycelerView(homeItems)
                }

                else -> {
                    progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun createHomeAdapter(): ListDelegationAdapter<List<HomeItem>> {
        return ListDelegationAdapter(
            fastFilterListAdapterDelegate(
                fastFilterAdapterDelegate()
            ),
            headlineTextAdapterDelegate(),
            vacancyAdapterDelegate(
                onFavoriteChange = {

                },
                onButtonClick = {

                }
            ),
            buttonAdapterDelegate{

            }
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
    private fun updateRecycelerView(homeItems: List<HomeItem>) {
        homeAdapter.items = homeItems
        homeAdapter.notifyDataSetChanged()
    }
}