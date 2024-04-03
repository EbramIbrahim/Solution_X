package com.example.solutionxarch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.*
import androidx.lifecycle.lifecycleScope

import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.databinding.ActivityMainBinding
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.presentation.LoginEvent
import com.example.solutionxarch.features.login.presentation.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel: LoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.loginBtn.setOnClickListener {
            val countryCode = binding.countryCode.text.toString()
            val phone = binding.phoneNumber.text.toString()
            val password = binding.password.text.toString()
            val phoneRequest = PhoneRequest(countryCode, phone)
            val userRequest = UserRequest(phoneRequest, password)
            viewModel.onEvent(
                LoginEvent.UserLogin(userRequest)
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect {
                    Toast.makeText(this@MainActivity, it.userEntity.toString() ?: "", Toast.LENGTH_LONG).show()
                }
            }
        }


    }

}






