package com.example.solutionxarch.features.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.solutionxarch.R
import com.example.solutionxarch.core.presentation.helper.BaseFragment
import com.example.solutionxarch.core.presentation.helper.state_logger.IStateLogger
import com.example.solutionxarch.core.presentation.helper.state_logger.StateLogger
import com.example.solutionxarch.databinding.FragmentLoginBinding
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), IStateLogger by StateLogger() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLifecycleOwner(this)

        collectLoginState()

        val countryCode = views.countryCode.text.toString()
        val phoneNumber = views.phoneNumber.text.toString()
        val password = views.password.text.toString()
        val userRequest = UserRequest(
            PhoneRequest("0020", "100100100"),
            "123456789"
        )

        views.loginBtn.setOnClickListener {
            userLogin(userRequest)
        }

    }

    private fun userLogin(userRequest: UserRequest) {
        loginViewModel.onEvent(LoginEvent.UserLogin(userRequest))
    }

    private fun collectLoginState() {
        collectStateWithLifeCycle(loginViewModel.loginState) { state ->
            if (state.user != null) {
                findNavController().navigate(R.id.updateListFragment)
            }
        }
    }
}




