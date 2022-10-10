package com.hammad.riyadh.helper

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import com.hammad.riyadh.RiyadhApp
import java.util.*

/**
 * [LocaleHelper] is class which provide some method,
 * which help to easily change or update the language
 */
object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private val preferences by lazy { RiyadhApp.get().mPrefs }

    /**
     * Provide default save language
     */
    fun getLanguage(): String? {
        return getPersistedData(Locale.getDefault().language)
    }

    /**
     * Save the locale language and provide the context through which we can easily access the resource file
     */
    fun setLocale(context: Context, language: String): Context {
        persist(language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
    }


    private fun getPersistedData(defaultLanguage: String): String? {
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage) //saving data
    }

    private fun persist(language: String?) {
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    /**
     * update the locale language and provide [ContextWrapper] class
     * @param context Base context of the activity
     * @param language in which you want to save
     * @return [ContextWrapper]
     */
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

    /**
     * update the locale language and provide [ContextWrapper] class
     * @param context Base context of the activity
     * @param localeToSwitchTo in which you want to save
     * @return [ContextWrapper]
     */
    fun updateLocale(context: Context, localeToSwitchTo: Locale): ContextWrapper {
        var c = context
        val resources: Resources = c.resources
        val configuration: Configuration = resources.configuration // 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(localeToSwitchTo) // 2
            LocaleList.setDefault(localeList) // 3
            configuration.setLocales(localeList) // 4
        } else {
            configuration.locale = localeToSwitchTo // 5
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            c = c.createConfigurationContext(configuration) // 6
        } else {
            resources.updateConfiguration(configuration, resources.displayMetrics) // 7
        }
        return ContextWrapper(c) // 8
    }
}