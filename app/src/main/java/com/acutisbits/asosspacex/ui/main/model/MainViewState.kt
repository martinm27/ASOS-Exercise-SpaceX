package com.acutisbits.asosspacex.ui.main.model

sealed class MainViewState {
    object LoadingViewState : MainViewState()
    object ErrorViewState : MainViewState()
    object EmptyViewState : MainViewState()
    data class ResultViewState(val description: String, val launchesList: List<LaunchViewState>) : MainViewState()
}
