package com.acutisbits.asosspacex.ui.main.model

sealed class MainViewState {
    object LoadingViewState : MainViewState()
    data class ErrorViewState(val message: String) : MainViewState()
    data class CompanyViewState(val description: String) : MainViewState()
    data class LaunchesViewState(val launchesList: List<LaunchViewState>) : MainViewState()
}
