package com.example.documenteditor.templatesFun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorAlert(){
    var dialogVisible by remember { mutableStateOf(true) }

    if(dialogVisible){
        AlertDialog(
            onDismissRequest = {dialogVisible = false},
            confirmButton = {
                TextButton({dialogVisible=false}) {
                    Text("Ok")
                }
            },
            title = { Text("Что-то пошло не так") }
        )
    }
}