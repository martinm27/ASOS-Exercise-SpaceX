package com.acutisbits.asosspacex.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.acutisbits.asosspacex.navigation.Router
import com.acutisbits.asosspacex.navigation.RouterImpl
import org.koin.dsl.module

fun appModule() = module {

    factory<Router> {
        val activity: AppCompatActivity = it[0]
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        RouterImpl(activity, fragmentManager)
    }
}
