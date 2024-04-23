package com.example.solutionxarch

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.*
import com.example.solutionxarch.databinding.ActivityMainBinding
import com.example.solutionxarch.features.login.presentation.LoginViewModel
import com.example.solutionxarch.features.save_list.presentation.ListValuesEvent
import com.example.solutionxarch.features.save_list.presentation.ListValuesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel: LoginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        val listValuesViewModel: ListValuesViewModel =
            ViewModelProvider(this)[ListValuesViewModel::class.java]


        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val newConfiguration: Configuration = with(Configuration(resources.configuration)) {
            setLocale(Locale.forLanguageTag("ar"))
            this
        }



        binding.setEnglishNamesBtn.setOnClickListener {

            val names = listOf(
                getString(R.string.James),
                getString(R.string.Robert),
                getString(R.string.John),
                getString(R.string.Michael),
                getString(R.string.David),
                getString(R.string.William),
                getString(R.string.Richard),
                getString(R.string.Joseph),
            )
            listValuesViewModel.onEvent(ListValuesEvent.SaveListValues(names))
        }

        binding.updateBtn.setOnClickListener {
            val arabicNamesList = listOf(
                createConfigurationContext(newConfiguration).getString(R.string.James),
                createConfigurationContext(newConfiguration).getString(R.string.Robert),
                createConfigurationContext(newConfiguration).getString(R.string.John),
                createConfigurationContext(newConfiguration).getString(R.string.Michael),
                createConfigurationContext(newConfiguration).getString(R.string.David),
                createConfigurationContext(newConfiguration).getString(R.string.William),
                createConfigurationContext(newConfiguration).getString(R.string.Richard),
                createConfigurationContext(newConfiguration).getString(R.string.Joseph),
            )
            listValuesViewModel.onEvent(ListValuesEvent.UpdateListValuesWorkManager(arabicNamesList))
        }

        binding.getBtn.setOnClickListener {
            listValuesViewModel.onEvent(ListValuesEvent.GetListValues)
        }


    }

}






