package com.example.databasetest

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.databasetest.pref.ISettingsChanged
import com.example.databasetest.pref.LocaleHelper
import com.example.databasetest.room.ExerciseRepository

class SettingsActivity : AppCompatActivity(),ISettingsChanged {
    var isNight:Boolean=false
    var isFontChanged:Boolean=false
    var isEnglish:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment.newInstance(this))
            .commit()
    }

    override fun onDayThemeSet() {
        isNight=false
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
        recreate()

    }

    override fun onNightThemeSet() {
        isNight=true
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
        recreate()
    }

    override fun onFontChanged() {
        val configuration: Configuration = resources.configuration
        configuration.fontScale*=1.25.toFloat()
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        recreate()
    }


    override fun onFontDecreased() {
        val configuration: Configuration = resources.configuration
        configuration.fontScale/=1.25.toFloat()
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        recreate()
    }

    override fun onDataClicked() {
         ExerciseRepository(application).clean_all_data()
    }

    override fun onEnglishClecked() {
        LocaleHelper.setLocale(this, "be");
        recreate()
    }

    override fun onRussianClicked() {
        LocaleHelper.setLocale(this, "en");
        recreate()
    }




}