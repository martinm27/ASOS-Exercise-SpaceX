package com.acutisbits.asosspacex.navigation

import com.acutisbits.asosspacex.util.sort.SortingOrder

interface Router {

    fun finishHostActivity()

    fun goBack()

    fun showMain()

    fun showOpenLinkDialog(articleUrl: String, wikipediaUrl: String, videoUrl: String)

    fun showFilterDialog(filterAction: (year: String, isSuccessful: Boolean, sortingOrder: SortingOrder) -> Unit)
}
