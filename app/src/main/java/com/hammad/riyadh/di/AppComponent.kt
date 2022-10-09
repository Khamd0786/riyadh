package com.hammad.riyadh.di

import com.hammad.riyadh.view.activities.MainActivity
import com.hammad.riyadh.view.fragments.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)


}