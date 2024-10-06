package com.effective.response

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.effective.navigation.navController
import com.effective.response.databinding.FragmentResponseBinding
import com.effective.ui.colors.toHex
import com.effective.ui.flow.collectWithLifecycle
import com.effective.ui.res.getAttrColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResponseFragment : Fragment(R.layout.fragment_response) {

    companion object {
        const val TAG = "response"
        const val ARG = "vacancy_title"
    }

    private lateinit var binding: FragmentResponseBinding
    private val viewModel: ResponseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResponseBinding.bind(view)

        setScreenBackgroundColor()
        setVacancyTitle()

        binding.textButton.setOnClickListener {
            viewModel.setCenterTrue()
        }

        binding.button.setOnClickListener {
            navController.navigateBack()
        }

        viewModel.isCenterState.collectWithLifecycle(viewLifecycleOwner) { isCenter ->
            if (isCenter) {
                binding.card.layoutParams = FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
                binding.coverLetter.visibility = View.VISIBLE
                binding.textButton.visibility = View.GONE
                binding.bottomSpace.visibility = View.GONE
            } else {
                binding.card.layoutParams = FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM
                )
                binding.coverLetter.visibility = View.GONE
                binding.textButton.visibility = View.VISIBLE
                binding.bottomSpace.visibility = View.VISIBLE
            }
        }
    }

    private fun setScreenBackgroundColor() {
        binding.root.setOnClickListener { navController.navigateBack() }
        binding.card.setOnClickListener { }

        val backgroundColor =
            requireContext().getAttrColor(com.google.android.material.R.attr.backgroundColor)

        val newColor = Color.parseColor(backgroundColor.toHex(0.9f))

        binding.root.background = newColor.toDrawable()
    }

    private fun setVacancyTitle() {
        val title = requireArguments().getString(ARG) ?: return
        binding.vacancyTitle.text = title
    }


}