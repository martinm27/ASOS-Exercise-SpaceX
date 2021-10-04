package com.acutisbits.asosspacex.di

import android.content.res.Resources
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.coreui.utils.CircularProgressDrawableFactory
import com.acutisbits.asosspacex.imageloader.ImageQueryLoader
import com.acutisbits.asosspacex.imageloader.ImageQueryLoaderImpl
import com.acutisbits.asosspacex.navigation.Router
import com.acutisbits.asosspacex.navigation.RouterImpl
import com.acutisbits.asosspacex.ui.main.ui.LaunchesAdapter
import com.acutisbits.asosspacex.ui.main.ui.MainViewModel
import com.acutisbits.asosspacex.util.DateUtils
import com.acutisbits.asosspacex.util.DateUtilsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    factory<Router> {
        val activity: AppCompatActivity = it[0]
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        RouterImpl(activity, fragmentManager)
    }

    factory {
        val layoutInflater: LayoutInflater = it[0]
        val onClickListener: (String, String, String) -> Unit = it[1]
        LaunchesAdapter(layoutInflater, get(), onClickListener)
    }

    viewModel {
        MainViewModel(get(), get(), get(), get(), get(), get())
    }

    single<Resources> { androidContext().resources }
    single<DateUtils> { DateUtilsImpl() }

    single {
        CircularProgressDrawableFactory(
            androidContext(),
            androidContext().resources.getDimension(R.dimen.default_spinner_radius)
        )
    }
    single<ImageQueryLoader> { ImageQueryLoaderImpl(androidContext(), get()) }
}
