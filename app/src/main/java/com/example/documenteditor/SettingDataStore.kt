package com.example.documenteditor

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "Settings")

class SettingDataStore(private val dataStore: DataStore<Preferences>): ViewModel() {
    // Функция для записи значения boolean
    suspend fun booleanChanges(key: Preferences.Key<Boolean>,value: Boolean) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }


    val saveValuesKey: Preferences.Key<Boolean> = booleanPreferencesKey("SaveValueSetting")
    val isSaveValue: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[saveValuesKey] ?: true // по умолчанию true
        }


    val showAlertFlagKey = booleanPreferencesKey("ShowAlertFlagKey")
    val showAlertFlag: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[showAlertFlagKey] ?: true // по умолчанию true
        }


    val askAlertKey = booleanPreferencesKey("AskAlertKey")
    val askAlert: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[askAlertKey] ?: true // по умолчанию true
        }
}