package com.hammad.riyadh

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hammad.riyadh.di.AppComponent
import com.hammad.riyadh.di.AppModule
import com.hammad.riyadh.di.DaggerAppComponent

class RiyadhApp : Application() {

    lateinit var mPrefs: SharedPreferences
    private val prefKey = "_DEFAULT_PREFERENCE_FILE_KEY"

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()

        app = this
        mPrefs = getSharedPreferences(prefKey, Context.MODE_PRIVATE)

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()


    }

    companion object {
        private lateinit var app: RiyadhApp
        fun get(): RiyadhApp = app
    }


}