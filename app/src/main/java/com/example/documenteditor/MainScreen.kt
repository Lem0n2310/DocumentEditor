package com.example.documenteditor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xff9DA7E8)),
        contentAlignment = Alignment.Center // Бокс на весь экран, контент по центру
    ){
        // Три кнопки в колонке
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {navController.navigate(TemplatePickerRoute)},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )
                ) {
                Text("Создать документ по шаблону")
            }

            Button({navController.navigate(Screen2Route("my title"))},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                )
                ) {
                Text("Создать шаблон")
            }

            Button({navController.navigate(Screen2Route("my title"))},
                    colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                containerColor = Color.White
            )
            ) {
                Text("Недавние проекты")
            }
        }
    }
}