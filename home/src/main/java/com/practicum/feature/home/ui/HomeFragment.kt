package com.practicum.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practicum.core.BaseFragment
import com.practicum.feature.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}