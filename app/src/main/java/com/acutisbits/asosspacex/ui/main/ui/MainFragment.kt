package com.acutisbits.asosspacex.ui.main.ui

import android.os.Bundle
import android.view.View
import com.acutisbits.asosspacex.coreui.BaseFragment
import com.acutisbits.asosspacex.databinding.FragmentMainBinding
import com.acutisbits.asosspacex.ui.main.model.MainViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : BaseFragment<MainViewState, FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        const val TAG = "MainFragment"
    }

    override val model: MainViewModel by viewModel()

    private val adapter: LaunchesAdapter by inject(
        parameters = { parametersOf(layoutInflater) }
    )

    override fun FragmentMainBinding.initialiseView(view: View, savedInstanceState: Bundle?) {
        binding.spaceXLaunchesRecyclerView.adapter = adapter
    }

    override fun FragmentMainBinding.render(viewState: MainViewState) {
        with(viewState) {
            binding.spaceXCompanyDescription.text = companyDescription
            adapter.submitList(launchesList)
        }
    }
}
