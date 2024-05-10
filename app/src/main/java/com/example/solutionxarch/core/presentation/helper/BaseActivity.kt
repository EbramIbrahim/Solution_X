package com.example.solutionxarch.core.presentation.helper

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.example.solutionxarch.R
import com.example.solutionxarch.core.common.SolutionXException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseActivity<binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> binding
) : AppCompatActivity(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {


    protected lateinit var views: binding
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = bindingInflater.invoke(layoutInflater).apply {
            setContentView(root)
        }

        onViewBindingCreated(savedInstanceState)


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    abstract fun onViewBindingCreated(savedInstanceState: Bundle?)

    protected fun networkErrorHandler(exception: SolutionXException) {
        TODO("Not yet implemented")
    }

    @CallSuper
    override fun onDestroy() {
        coroutineContext[Job]?.cancel()
        super.onDestroy()
    }
}







