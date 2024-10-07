package com.effective.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.effective.favorite.databinding.FragmentFavoriteBinding
import com.effective.navigation.navController
import com.effective.ui.flow.collectWithLifecycle
import com.effective.ui.metrics.dpRoundToPx
import com.effective.ui.recycler.RecylerItem
import com.effective.ui.recycler.adapters.toUi
import com.effective.ui.recycler.adapters.vacancyAdapterDelegate
import com.effective.ui.recycler.decorations.BottomMarginDecoration
import com.effective.ui.recycler.decorations.TopFirstElementMarginDecoration
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.effective.resources.R as Res


@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {


    companion object {
        const val TAG = "favorite"
    }

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val favoriteAdapter = createAdapter()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)

        createRecyler()


        favoriteViewModel.favoriteVacancies.collectWithLifecycle(viewLifecycleOwner) { vacancies ->
            binding.vacanciesCount.text = requireContext().resources.getQuantityString(
                Res.plurals.vacancies_count,
                vacancies.size,
                vacancies.size
            )
            favoriteAdapter.items = vacancies.map { it.toUi(requireContext()) }
            favoriteAdapter.notifyDataSetChanged()
        }
    }

    private fun createRecyler() {
        val homeRecyclerView = binding.favoriteRecylerView
        homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        homeRecyclerView.adapter = favoriteAdapter
        homeRecyclerView.addItemDecoration(BottomMarginDecoration(8.dpRoundToPx(requireContext())))
        homeRecyclerView.addItemDecoration(
            TopFirstElementMarginDecoration(
                16.dpRoundToPx(requireContext())
            )
        )
    }

    private fun createAdapter(): ListDelegationAdapter<List<RecylerItem>> {
        return ListDelegationAdapter(
            vacancyAdapterDelegate(
                onFavoriteChange = { vacancyItem ->
                    favoriteViewModel.changeVacancyFavorite(vacancyItem)
                },
                onButtonClick = {
                    navController.navigateToResponse(it.title)
                },
                onCardClick = {
                    navController.navigateToVacancy(it.id)
                }
            )
        )
    }
}