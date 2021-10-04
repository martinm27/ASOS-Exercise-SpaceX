package com.acutisbits.asosspacex.navigation

import com.acutisbits.asosspacex.util.sort.SortingOrder

interface Router {

    fun finishHostActivity()

    fun clearAll()

    fun goBack()

    fun showMain()

    fun showOpenLinkDialog(articleUrl: String, wikipediaUrl: String, videoUrl: String)

    fun showFilterDialog(filerAction: (year: String, isSuccessful: Boolean, sortingOrder: SortingOrder) -> Unit)
}
