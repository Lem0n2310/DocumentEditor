package com.example.documenteditor

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.documenteditor.templatesFun.FieldValuesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(templates: List<DocumentTemplate>, templateId: Int, navController: NavHostController, workFile: String) {
    // Хранение значений полей в виде словаря (ключ - id поля, значение - введенное пользователем значение)
    val context = LocalContext.current

    val saveViewModel = viewModel<SaveViewModel>()
    val fieldViewModel = viewModel<FieldValuesViewModel>()

    var selectedTemplate by remember { mutableStateOf(templates[templateId]) }


    val createDocumentLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("*/*")) {uri: Uri? ->
        uri?.let {
            saveViewModel.save(context, it, selectedTemplate, fieldViewModel.fieldValues, workFile)
        }
    }

    val save: () -> Unit = {
        createDocumentLauncher.launch(selectedTemplate.nameForUser + ".docx")
    }


    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xff9DA7E8)),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 100.dp, bottom = 80.dp).imePadding()
        )
            { // Используем Column для вертикального расположения полей
                // Проходим по всем полям выбранного шаблона
                items(selectedTemplate.fields) {field->
                    // В зависимости от типа поля отображаем соответствующий компонент
                    // Текстовое поле для ввода
                    TextField(
                        modifier =Modifier.padding(vertical = 10.dp),
                        value = fieldViewModel.fieldValues[field.label]?: "", // Получаем текущее значение или пустую строку
                        onValueChange = {
                            fieldViewModel.updateValue(key= field.label, value = it)
                            }, // Обновляем значение при изменении
                        label = { Text(field.label) } // Отображаем метку поля
                    )
                }
        }
    }

    // Снэек бар с подписью местонахождения и кнопкой назад
    TopAppBar(
        title = { Text(selectedTemplate.nameForUser) },
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
            IconButton(onClick = { save() }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
        }
    )
}

