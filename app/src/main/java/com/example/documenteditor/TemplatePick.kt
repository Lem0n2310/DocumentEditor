package com.example.documenteditor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
// Выбор шаблона
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatePicker(templates: List<DocumentTemplate>, navController: NavHostController) { // Получаем список шаблонов и нав
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xff9DA7E8)),
        contentAlignment = Alignment.Center,
    ) {
        // ленивая колонка на весь экран
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {// Перебираем все шаблоны из templates и под каждого создаем свою кнопку
                templates.forEach { template ->
                    Button(onClick = { navController.navigate(TemplateRoute(template.id, template.nameForDevelop)) },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White
                        )
                        ) {
                        Text(template.nameForUser)
                    }
                }
            }
        }
    }

    // Снэек бар с подписью местонахождения и кнопкой назад
    TopAppBar(
        title = { Text("Выбор шаблона") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xff8192fe),
            titleContentColor =  Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}


