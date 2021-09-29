package com.acutisbits.asosspacex.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.databinding.ActivityMainBinding
import com.acutisbits.asosspacex.navigation.Router
import com.acutisbits.asosspacex.navigation.RoutingActionConsumer
import com.acutisbits.asosspacex.navigation.RoutingActionsSource
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main), RoutingActionConsumer {

    private val routingActionsSource: RoutingActionsSource by inject()
    private val router: Router by inject(parameters = { parametersOf(this) })

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            router.showMain()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        routingActionsSource.setActiveRoutingActionConsumer(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        routingActionsSource.unsetRoutingActionConsumer(this)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        if (shouldUnsetRoutingActionConsumer()) {
            routingActionsSource.unsetRoutingActionConsumer(this)
        }
        super.onPause()
    }

    private fun shouldUnsetRoutingActionConsumer() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) isInMultiWindowMode.not() else true

    override fun onRoutingAction(routingAction: (Router) -> Unit) = routingAction(router)

    override fun onBackPressed() = router.goBack()
}
