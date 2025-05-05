package com.example.documenteditor.templatesFun

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class FieldValuesViewModel: ViewModel() {
    val fieldValues =  mutableStateMapOf<String, String>()

    fun updateValue(key: String, value: String){
        fieldValues[key] = value
    }

    fun clearValues(){
        fieldValues.clear()
    }
}