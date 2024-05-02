package com.example.coroutine.data.dataStor

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val PREFERENCES_NAMME = "is_first_time"
val Context.dataStore : androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "settings")
class MovieAppDataStor @Inject constructor(context: Context) {

    private val onBoardingScreenKey = booleanPreferencesKey(name = PREFERENCES_NAMME)

    private val dataStore = context.dataStore

    suspend fun saveonBoardingSate (showTipsPage : Boolean){
        dataStore.edit { preferences ->
            preferences[onBoardingScreenKey] = showTipsPage
        }
    }
    fun readonBoardingSate() : Flow<Boolean> {
        return dataStore.data.map {  preferences ->
            preferences[onBoardingScreenKey] ?: false
        }
    }
}