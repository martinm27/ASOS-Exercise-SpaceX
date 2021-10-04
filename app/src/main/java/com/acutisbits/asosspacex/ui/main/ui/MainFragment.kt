package com.acutisbits.asosspacex.ui.main.ui

import android.os.Bundle
import android.view.View
import com.acutisbits.asosspacex.coreui.BaseFragment
import com.acutisbits.asosspacex.coreui.utils.hide
import com.acutisbits.asosspacex.coreui.utils.show
import com.acutisbits.asosspacex.databinding.FragmentMainBinding
import com.acutisbits.asosspacex.ui.main.model.MainViewState
import com.acutisbits.asosspacex.ui.main.model.MainViewState.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : BaseFragment<MainViewState, FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        const val TAG = "MainFragment"
    }

    override val model: MainViewModel by viewModel()

    private val adapter: LaunchesAdapter by inject(
        parameters = { parametersOf(layoutInflater, model::showOpenLinkDialog) }
    )

    override fun FragmentMainBinding.initialiseView(view: View, savedInstanceState: Bundle?) {
        spaceXLaunchesRecyclerView.adapter = adapter
        spaceXErrorButton.setOnClickListener { model.tryAgain() }
        spaceXFilterIcon.setOnClickListener { model.showFilterDialog() }
    }

    override fun FragmentMainBinding.render(viewState: MainViewState) = when (viewState) {
        is ResultViewState -> {
            spaceXCompanyDescription.text = viewState.description
            adapter.submitList(viewState.launchesList)

            launchesLoadingSpinner.hide()
            spaceXErrorButton.hide()

            spaceXCompanyDescription.show()
            spaceXLaunchesRecyclerView.show()
        }
        LoadingViewState -> {
            launchesLoadingSpinner.show()
        }
        ErrorViewState -> {
            launchesLoadingSpinner.hide()
            spaceXCompanyDescription.hide()
            spaceXLaunchesRecyclerView.hide()
            spaceXErrorButton.show()
        }
    }
}
