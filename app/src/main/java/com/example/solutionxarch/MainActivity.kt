package com.example.solutionxarch

import android.os.Bundle
import com.example.solutionxarch.core.presentation.helper.BaseActivity
import com.example.solutionxarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
    }
}






