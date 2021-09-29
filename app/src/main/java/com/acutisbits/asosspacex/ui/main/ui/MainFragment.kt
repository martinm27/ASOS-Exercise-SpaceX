package com.acutisbits.asosspacex.ui.main.ui

import android.os.Bundle
import android.view.View
import com.acutisbits.asosspacex.coreui.BaseFragment
import com.acutisbits.asosspacex.databinding.FragmentMainBinding
import com.acutisbits.asosspacex.ui.main.model.MainViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainViewState, FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        const val TAG = "MainFragment"
    }

    override val model: MainViewModel by viewModel()

    override fun FragmentMainBinding.initialiseView(view: View, savedInstanceState: Bundle?) {

    }

    override fun FragmentMainBinding.render(viewState: MainViewState) {

    }
}
