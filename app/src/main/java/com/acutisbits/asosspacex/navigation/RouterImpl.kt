package com.acutisbits.asosspacex.navigation

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.ui.main.ui.MainFragment
import com.acutisbits.asosspacex.util.sort.SortingOrder

private const val LAST_FRAGMENT = 0

@IdRes
private const val MAIN_FLOW_CONTAINER = R.id.activity_main_container

@Suppress("TooManyFunctions")
class RouterImpl(
    private val activity: AppCompatActivity,
    fragmentManager: FragmentManager
) : CloseableRouter(fragmentManager), Router {

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

    override fun showFilterDialog(filerAction: (year: String, isSuccessful: Boolean, sortingOrder: SortingOrder) -> Unit) {
        val alertDialog = AlertDialog.Builder(activity)
        val customLayout: View = activity.layoutInflater.inflate(R.layout.filter_dialog, null)
        alertDialog.setView(customLayout)

        val alert = alertDialog.create().apply {
            show()
        }

    }

    fun openUrlInDefaultApp(url: String) {
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

    override fun clearAll() = fragmentManager.safeClearBackStack()
}
