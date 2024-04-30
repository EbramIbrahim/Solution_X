package com.example.solutionxarch

import android.os.Bundle
import android.view.LayoutInflater
import com.example.solutionxarch.core.presentation.helper.BaseActivity
import com.example.solutionxarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {}
}






