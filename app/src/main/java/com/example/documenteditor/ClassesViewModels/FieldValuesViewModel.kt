package com.example.documenteditor.ClassesViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FieldValuesViewModel: ViewModel() {
    val fieldValues =  mutableStateMapOf<String, String>()
    var forFlag by mutableStateOf(true)

    fun updateValue(key: String, value: String){ // Обновление данных
        fieldValues[key] = value
    }

    fun clearValues(){
        fieldValues.clear() // очищение словаря
    }
}