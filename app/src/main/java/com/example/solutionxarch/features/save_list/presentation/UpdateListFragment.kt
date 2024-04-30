package com.example.solutionxarch.features.save_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.solutionxarch.R
import com.example.solutionxarch.core.presentation.helper.BaseFragment
import com.example.solutionxarch.databinding.FragmentUpdateListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListFragment: BaseFragment<FragmentUpdateListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateListBinding
        get() = FragmentUpdateListBinding::inflate

    private val listValuesViewModel by viewModels<ListValuesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views.setEnglishNamesBtn.setOnClickListener {

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

        views.updateBtn.setOnClickListener {
            val arabicNamesList = listOf(
                "جيمس",
                "روبرت",
                "جون",
                "ميخائيل",
                "ديفيد",
                "ويليم",
                "ريتشارد",
                "جوزيف",
            )
            listValuesViewModel.onEvent(ListValuesEvent.UpdateListValuesWorkManager(arabicNamesList))
        }

        views.getBtn.setOnClickListener {
            listValuesViewModel.onEvent(ListValuesEvent.GetListValues)
        }
    }
}