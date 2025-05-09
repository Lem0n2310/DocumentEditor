package com.example.documenteditor.ClassesViewModels

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class FieldValuesViewModel: ViewModel() {
    val fieldValues =  mutableStateMapOf<String, String>()

    fun updateValue(key: String, value: String){ // Обновление данных
        fieldValues[key] = value
    }

    fun clearValues(){
        fieldValues.clear() // очищение словаря
    }
}