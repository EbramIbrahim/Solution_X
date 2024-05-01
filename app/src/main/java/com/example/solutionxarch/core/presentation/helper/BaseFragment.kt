package com.example.solutionxarch.core.presentation.helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<binding : ViewBinding> : Fragment(){

        protected lateinit var views: binding
            private set

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = bindingInflater.invoke(inflater, container, false)
        return views.root
    }

    protected fun <T> collectStateWithLifeCycle(flow: Flow<T>, block: (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest {
                    block(it)
                }
            }
        }
    }

}










