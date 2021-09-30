package com.acutisbits.asosspacex.navigation

interface Router {

    fun finishHostActivity()

    fun clearAll()

    fun goBack()

    fun showMain()

    fun showOpenLinkDialog(articleUrl: String, wikipediaUrl: String, videoUrl: String)
}
