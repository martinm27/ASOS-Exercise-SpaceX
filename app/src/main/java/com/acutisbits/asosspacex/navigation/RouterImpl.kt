package com.acutisbits.asosspacex.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

private const val LAST_FRAGMENT = 0

@Suppress("TooManyFunctions")
class RouterImpl(
    private val activity: AppCompatActivity,
    fragmentManager: FragmentManager
) : CloseableRouter(fragmentManager), Router {

    override fun goBack() = dispatchOnMainThreadWithThrottle(this::goBackInternal)

    private fun goBackInternal() {
        if (fragmentManager.backStackEntryCount != LAST_FRAGMENT) {
            fragmentManager.popBackStackImmediate()
        } else {
            finishHostActivity()
        }
    }

    override fun finishHostActivity() = activity.finish()

    override fun clearAll() = fragmentManager.safeClearBackStack()
}
