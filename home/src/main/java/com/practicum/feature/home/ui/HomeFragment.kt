package com.practicum.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.practicum.core.BaseFragment
import com.practicum.feature.home.R
import com.practicum.feature.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var bottomSheetSort: BottomSheetBehavior<LinearLayout>? = null
    private var bottomSheetFilter: BottomSheetBehavior<LinearLayout>? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetSort = BottomSheetBehavior.from(binding.sortingBottomSheet)
            .apply { state = BottomSheetBehavior.STATE_HIDDEN }

        bottomSheetFilter = BottomSheetBehavior.from(binding.filtersBottomSheet)
            .apply { state = BottomSheetBehavior.STATE_HIDDEN }

        val categories = listOf("Категория", "Kotlin", "Android", "UI/UX", "Java", "Frontend")
        val difficultyLevels = listOf("Уровень", "Начинающий", "Продолжающий", "Профи")
        val priceOptions = listOf("Стоимость", "Платный", "Бесплатный")

        setupSpinner(binding.categorySpinner, categories)
        setupSpinner(binding.difficultSpinner, difficultyLevels)
        setupSpinner(binding.priceSpinner, priceOptions)

        binding.applyFiltersButton.setOnClickListener {
            bottomSheetFilter?.state = BottomSheetBehavior.STATE_HIDDEN
            val selectedCategory = binding.categorySpinner.selectedItem.toString()
            val selectedDifficult = binding.difficultSpinner.selectedItem.toString()
            val selectedPrice = binding.priceSpinner.selectedItem.toString()
            // применение выбранных фильтров
        }

        binding.filterBtn.setOnClickListener {
            bottomSheetFilter?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.sortGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sortDate -> {
                    binding.sortedBtn.text = getString(com.practicum.core.R.string.sort_to_date)
                    bottomSheetSort?.state = BottomSheetBehavior.STATE_HIDDEN
                }

                R.id.sortPopularity -> {
                    binding.sortedBtn.text = getString(com.practicum.core.R.string.sort_popularity)
                    bottomSheetSort?.state = BottomSheetBehavior.STATE_HIDDEN
                }

                R.id.sortRating -> {
                    binding.sortedBtn.text = getString(com.practicum.core.R.string.sort_rating)
                    bottomSheetSort?.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }
        }

        binding.sortedBtn.setOnClickListener {
            bottomSheetSort?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        bottomSheetSort?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.overlay.isVisible = true
                        animateOverlay(true, binding.overlay)
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.overlay.isVisible = false
                        animateOverlay(false, binding.overlay)
                    }

                    else -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        bottomSheetFilter?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.overlay.isVisible = true
                        animateOverlay(true, binding.overlay)
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.overlay.isVisible = false
                        animateOverlay(false, binding.overlay)
                    }

                    else -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

    }

    override fun onDestroy() {
        bottomSheetSort = null
        bottomSheetFilter = null
        super.onDestroy()
    }

    private fun setupSpinner(spinner: Spinner, items: List<String>) {
        val adapter =
            ArrayAdapter(requireContext(), com.practicum.core.R.layout.filter_item, items).apply {
                setDropDownViewResource(
                    com.practicum.core.R.layout.filter_item
                )
            }
        spinner.adapter = adapter
    }

    private fun animateOverlay(show: Boolean, view: View) {
        val alpha = if (show) 1f else 0f
        view.animate()
            .alpha(alpha)
            .setDuration(ANIMATE_DURATION)
            .withEndAction { if (!show) view.isVisible = false }
    }

    companion object {
        private const val ANIMATE_DURATION = 300L
    }

}