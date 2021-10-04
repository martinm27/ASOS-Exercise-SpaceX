package com.acutisbits.asosspacex.navigation

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.core.MAX_YEAR
import com.acutisbits.asosspacex.core.MIN_YEAR
import com.acutisbits.asosspacex.ui.main.ui.MainFragment
import com.acutisbits.asosspacex.util.sort.SortingOrder

private const val LAST_FRAGMENT = 0

private const val ROUTING_ACTION_THROTTLE_WINDOW = 300

@IdRes
private const val MAIN_FLOW_CONTAINER = R.id.activity_main_container

@Suppress("TooManyFunctions")
class RouterImpl(
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager
) : Router {

    private val mainHandler = Handler(Looper.getMainLooper())

    private var lastActionStamp = 0L

    init {
        fragmentManager.addOnBackStackChangedListener {
            mainHandler.post {
                fragmentManager.peekBackStack()?.let {
                    if (!it.name.isNullOrBlank()) {
                        fragmentManager.popBackStackImmediate()
                    }
                }
            }
        }
    }

    /** Dispatches action on main thread by posting the action. If the current thread is main thread, action is executed immediately. */
    private fun dispatchOnMainThread(action: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            action()
        } else {
            mainHandler.post(action::invoke)
        }
    }

    private fun dispatchOnMainThreadWithThrottle(action: () -> Unit) {
        dispatchOnMainThread {
            System.currentTimeMillis().let {
                if (lastActionStamp < it - ROUTING_ACTION_THROTTLE_WINDOW) {
                    lastActionStamp = it
                    action()
                }
            }
        }
    }

    override fun showMain() {
        fragmentManager.inTransaction {
            replace(MAIN_FLOW_CONTAINER, MainFragment(), MainFragment.TAG)
        }
    }

    override fun showOpenLinkDialog(articleUrl: String, wikipediaUrl: String, videoUrl: String) {
        val alertDialog = AlertDialog.Builder(activity)
        val customLayout: View = activity.layoutInflater.inflate(R.layout.open_link_dialog, null)
        alertDialog.setView(customLayout)

        val alert = alertDialog.create().apply {
            show()
        }

        with(customLayout) {
            findViewById<TextView>(R.id.cancel_button).setOnClickListener {
                alert.dismiss()
            }

            findViewById<TextView>(R.id.openArticle).setOnClickListener {
                openUrlInDefaultApp(articleUrl)
            }

            findViewById<TextView>(R.id.openWikipedia).setOnClickListener {
                openUrlInDefaultApp(wikipediaUrl)
            }

            findViewById<TextView>(R.id.openVideo).setOnClickListener {
                openUrlInDefaultApp(videoUrl)
            }
        }
    }

    override fun showFilterDialog(filterAction: (year: String, isSuccessful: Boolean, sortingOrder: SortingOrder) -> Unit) {
        val alertDialog = AlertDialog.Builder(activity)
        val customLayout: View = activity.layoutInflater.inflate(R.layout.filter_dialog, null)
        alertDialog.setView(customLayout)

        val alert = alertDialog.create().apply {
            show()
        }

        with(customLayout) {
            val yearValue = findViewById<NumberPicker>(R.id.filterYearValue).apply {
                minValue = MIN_YEAR
                maxValue = MAX_YEAR - 1
                value = MAX_YEAR - 1
                val array = intArrayOf(*((MIN_YEAR until MAX_YEAR).toList().toIntArray())).map(Int::toString).toTypedArray()
                displayedValues = array
            }

            val isLaunchSuccessful = findViewById<RadioButton>(R.id.isLaunchSuccessfulPositive)
            val sortingOrder = findViewById<RadioButton>(R.id.sortingOrderAscending)

            findViewById<Button>(R.id.applyFilter).setOnClickListener {
                filterAction(
                    yearValue.value.toString(),
                    isLaunchSuccessful.isChecked,
                    if (sortingOrder.isChecked) SortingOrder.ASCENDING else SortingOrder.DESCENDING
                )
                alert.dismiss()
            }
        }
    }

    private fun openUrlInDefaultApp(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }

        if (openURL.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(openURL)
        }
    }

    override fun goBack() = dispatchOnMainThreadWithThrottle(this::goBackInternal)

    private fun goBackInternal() {
        if (fragmentManager.backStackEntryCount != LAST_FRAGMENT) {
            fragmentManager.popBackStackImmediate()
        } else {
            finishHostActivity()
        }
    }

    override fun finishHostActivity() = activity.finish()
}
