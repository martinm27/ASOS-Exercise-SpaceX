package com.acutisbits.asosspacex.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.acutisbits.asosspacex.R

private const val LAST_FRAGMENT = 0

@IdRes
private const val MAIN_FLOW_CONTAINER = R.id.activity_main_container

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
