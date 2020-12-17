package com.example.databasetest

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
import com.example.databasetest.pref.ContextUtils
import com.example.databasetest.pref.ISettingsChanged


class SettingsFragment : PreferenceFragmentCompat() {
    lateinit var settingsListner:ISettingsChanged
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferencescreen,rootKey)
        val themePreference:SwitchPreferenceCompat?=findPreference("theme_mode")
        themePreference?.onPreferenceChangeListener=object :Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if (themePreference!!.isChecked) {
                    settingsListner.onDayThemeSet()
                } else {
                    settingsListner.onNightThemeSet()
                }
                return true
            }
        }
        val localePreference:SwitchPreferenceCompat?=findPreference("lang_mode")
        localePreference?.onPreferenceChangeListener=object :Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if(localePreference!!.isChecked)
                    settingsListner.onEnglishClecked()
                else
                    settingsListner.onRussianClicked()
                return true
            }
        }
        val fontPreference:SwitchPreferenceCompat?=findPreference("font_size")
        fontPreference?.onPreferenceChangeListener=object :Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {

                if(fontPreference!!.isChecked)
                    settingsListner.onFontChanged()
                else
                    settingsListner.onFontDecreased()
                return true
            }
        }
        val cleanData:SwitchPreferenceCompat?=findPreference("clean_data")
        cleanData?.onPreferenceChangeListener=object:Preference.OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if(cleanData!!.isChecked)
                {
                    settingsListner.onDataClicked()
                }
                return true
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(settingsL:ISettingsChanged) =
            SettingsFragment().apply {
                settingsListner=settingsL
            }

    }

}