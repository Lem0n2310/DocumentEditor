package com.example.documenteditor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(templates: List<DocumentTemplate>, templateId: Int, navController: NavHostController) {
    // Хранение значений полей в виде карты (ключ - id поля, значение - введенное пользователем значение)
    val fieldValues = remember { mutableStateMapOf<String, String>() }

    var selectedTemplate by remember { mutableStateOf(templates[templateId]) }

    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xff9DA7E8)),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 100.dp, bottom = 80.dp)
        )
            { // Используем Column для вертикального расположения полей
                // Проходим по всем полям выбранного шаблона
                items(selectedTemplate.fields) {field->
                    // В зависимости от типа поля отображаем соответствующий компонент
                    // Текстовое поле для ввода
                    TextField(
                        modifier =Modifier.padding(vertical = 10.dp),
                        value = fieldValues[field.label]?: "", // Получаем текущее значение или пустую строку
                        onValueChange = {
                            fieldValues[field.label] = it
                            }, // Обновляем значение при изменении
                        label = { Text(field.label) } // Отображаем метку поля
                    )
                }
        }
    }

    // Снэек бар с подписью местонахождения и кнопкой назад
    TopAppBar(
        title = { Text(selectedTemplate.name) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xff8192fe),
            titleContentColor =  Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
        }
    )
}

