package com.example.documenteditor.ClassesViewModels

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

    // Настройка сохранения введенных значений (останутся ли они после сохранения файла)
    val saveValuesKey: Preferences.Key<Boolean> = booleanPreferencesKey("SaveValueSetting")
    val isSaveValue: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[saveValuesKey] ?: true // по умолчанию true
        }

    // Настройка диалога для сохранения введенных значений
    val showLeaveValueAlertFlagKey = booleanPreferencesKey("showLeaveValueAlertFlagKey")
    val showLeaveValueAlertFlag: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[showLeaveValueAlertFlagKey] ?: true // по умолчанию true
        }

    // Настройка проверки введе=ённых значений (все ли?)
    val showCheckValuesFlagKey = booleanPreferencesKey("isAllValuesFlagKey")
    val showCheckValuesFlag: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[showCheckValuesFlagKey] ?: true
        }
}