package com.effective.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.effective.home.databinding.FragmentHomeBinding
import com.effective.home.ui.decorations.BottomMarginDecoration
import com.effective.home.ui.adapter_delegates.FastFilterList
import com.effective.home.ui.adapter_delegates.HeaderText
import com.effective.home.ui.decorations.HeadlineDecoration
import com.effective.home.ui.common.HomeItem
import com.effective.home.ui.adapter_delegates.fastFilterAdapterDelegate
import com.effective.home.ui.adapter_delegates.fastFilterListAdapterDelegate
import com.effective.home.ui.adapter_delegates.headlineTextAdapterDelegate
import com.effective.home.ui.adapter_delegates.testFastFilterList
import com.effective.ui.metrics.dpRoundToPx
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject
import com.effective.resources.R as Res

class HomeFragment @Inject constructor() : Fragment(R.layout.fragment_home) {


    companion object {
        val Singleton = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        createRecyclerView()
    }

    private fun createHomeAdapter(): ListDelegationAdapter<List<HomeItem>> {
        val fastFilterListAdapterDelegate = fastFilterListAdapterDelegate(
            requireContext(),
            fastFilterAdapterDelegate()
        )

        return ListDelegationAdapter(
            fastFilterListAdapterDelegate,
            headlineTextAdapterDelegate()
        )
    }

    private fun createRecyclerView() {
        val homeRecyclerView = binding.homeRecyclerView
        homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val homeAdapter = createHomeAdapter()

        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.apply {
            addItemDecoration(BottomMarginDecoration(16.dpRoundToPx(requireContext())))
            addItemDecoration(HeadlineDecoration(16.dpRoundToPx(requireContext())))
        }

        homeAdapter.items = listOf(
            FastFilterList(testFastFilterList),
            HeaderText(getString(Res.string.vacancies_for_you))
        )

    }
}