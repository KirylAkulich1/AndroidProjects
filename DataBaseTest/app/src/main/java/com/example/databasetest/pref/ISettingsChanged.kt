package com.example.databasetest.pref

interface ISettingsChanged {
    fun onDayThemeSet()
    fun onNightThemeSet()
    fun onFontChanged()
    fun onFontDecreased()
    fun onDataClicked()
    fun onEnglishClecked()
    fun onRussianClicked()
}