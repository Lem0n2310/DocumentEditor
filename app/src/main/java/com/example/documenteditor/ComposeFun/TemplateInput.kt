@file:Suppress("DEPRECATION")

package com.example.documenteditor.ComposeFun

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.documenteditor.ClassesViewModels.DocumentTemplate
import com.example.documenteditor.ClassesViewModels.FieldValuesViewModel
import com.example.documenteditor.ClassesViewModels.SaveViewModel
import com.example.documenteditor.ClassesViewModels.SettingDataStore
import com.example.documenteditor.ClassesViewModels.dataStore
import com.example.documenteditor.functions.getFileNameFromUri
import com.example.documenteditor.functions.isFull
import database.Document
import database.DocumentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(templates: List<DocumentTemplate>, templateId: Int, navController: NavHostController, workFile: String) {
    // Контексты, корутина
    val context = LocalContext.current
    val contextFlag = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    // Вьюхи
    val saveViewModel = viewModel<SaveViewModel>() // вьюха сохранения
    val fieldViewModel = viewModel<FieldValuesViewModel>() // вьюха полей ввода
    val viewModel = viewModel<SettingDataStore>{ SettingDataStore(contextFlag.dataStore) } // Настройки
    val mDocumentViewModel = viewModel<DocumentViewModel>()

    // флаги
    val showCheckValuesFlagSetting = viewModel.showCheckValuesFlag.collectAsState(true) // Показывть окно проверки заполнения данных (вообще)
    var showCheckValuesDialog by remember { mutableStateOf(false) } //Показывать окно сохранения введенных значений (В нужный момент)
    val showLeaveValueAlertFlagSetting = viewModel.showLeaveValueAlertFlag.collectAsState(true) // Показывать окно сохранения введенных значений (для отображения вообще)
    var showLeaveValueDialog by remember { mutableStateOf(false) } // Показывать окно сохранения введенных значений (для отображения в нужный момент)
    val isSaveValue = viewModel.isSaveValue.collectAsState(true) // Сохранять введенные значения после сохранения
    var forFlag = fieldViewModel.forFlag// Флаг добавления всех полей шаблона в словарь при первом запуске

    // Шаблон
    val selectedTemplate by remember { mutableStateOf(templates[templateId]) } // Выбраный шаблон

    if (forFlag){
        for(field in selectedTemplate.fields){
            fieldViewModel.fieldValues[field.label] = "" // каждому полю присваеваем значение ""
        }
        fieldViewModel.forFlag = false //убираем флаг первого запуска
    }

    // функции
    val createDocumentLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("*/*")) {uri: Uri? ->
        uri?.let {
            saveViewModel.save(context, it, selectedTemplate, fieldViewModel.fieldValues, workFile)
            // Добавление нового экземпляра документа в датабазу
            val document: Document = Document(0, name = getFileNameFromUri(context, it).toString() , path = it.toString(), type = selectedTemplate.nameForUser)
            mDocumentViewModel.addDocument(document)
            Toast.makeText(context, "Документ создан!", Toast.LENGTH_SHORT).show()
        }

    }

    val save: () -> Unit = {
        createDocumentLauncher.launch(selectedTemplate.nameForUser + ".docx")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff9DA7E8)),
        contentAlignment = Alignment.Center,
    ) {
        // Снэек бар с подписью местонахождения и кнопкой назад
        TopAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
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
                IconButton(onClick = {
                    if (!fieldViewModel.fieldValues.isFull() && showCheckValuesFlagSetting.value) showCheckValuesDialog = true
                    else{
                        if (showLeaveValueAlertFlagSetting.value) { // Проверяем включено ли в настройках отбражение диалога
                            showLeaveValueDialog = true // включаем диалог
                        } else {
                            if (isSaveValue.value) {
                                save()
                            } // Проверяем включено ли в настройках сохранение значений, сохраняем
                            else {
                                save() // Сохраняем
                                coroutineScope.launch {
                                    delay(2000) // Через 2 секунды удаляем введенные занчения, если файл сохранен
                                    if (saveViewModel.isFileSaved) {
                                        fieldViewModel.clearValues()
                                    }
                                }
                            }
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                }
            }
        )

        if(showCheckValuesDialog){ // Отображение диалога подтверждения заполнения всех значений
            AlertDialog(
                onDismissRequest = {showCheckValuesDialog = false},
                confirmButton = { TextButton(onClick = {
                    showCheckValuesDialog = false
                    if (showLeaveValueAlertFlagSetting.value) { // Проверяем включено ли в настройках отбражение диалога
                        showLeaveValueDialog = true // включаем диалог
                    } else {
                        if (isSaveValue.value) {
                            save()
                        } // Проверяем включено ли в настройках сохранение значений, сохраняем
                        else {
                            save() // Сохраняем
                            coroutineScope.launch {
                                delay(2000) // Через 2 секунды удаляем введенные занчения, если файл сохранен
                                if (saveViewModel.isFileSaved) {
                                    fieldViewModel.clearValues()
                                }
                            }
                        }
                    }
                }) { Text("Всё равно сохранить")}},
                dismissButton = { TextButton(onClick = {showCheckValuesDialog = false}) { Text("Дополнить") }},
                title ={Text("Были заполнены не все строки")},
                text = {
                    Row {
                        Checkbox(
                            checked = !showCheckValuesFlagSetting.value,
                            onCheckedChange = { // если галочка стоит
                                showMessage(context = context, message = "Можно изменить в настройках")
                                coroutineScope.launch {
                                    viewModel.booleanChanges(viewModel.showCheckValuesFlagKey,!it) // Больше не спрашиваем перед сохраненением
                                }
                            }
                        )
                        Text("Больше не показывать это окно")
                    }
                }
            )
        }

        if(showLeaveValueDialog){// Диалог: Оставлять значения после сохранения
            AlertDialog(
                onDismissRequest = {showLeaveValueDialog = false}, // убираем диалог если нажали мимо
                confirmButton = { TextButton(onClick = {
                    coroutineScope.launch {viewModel.booleanChanges(viewModel.saveValuesKey, true)}
                    showLeaveValueDialog = false
                    save()
                }){ Text("Оставить")}},
                dismissButton = { TextButton(onClick = {
                    showLeaveValueDialog = false
                    save()
                    coroutineScope.launch {
                        viewModel.booleanChanges(viewModel.saveValuesKey, false)
                        delay(2000)// Через 2 секунды удаляем введенные занчения, если файл сохранен
                        if (saveViewModel.isFileSaved) {
                            fieldViewModel.clearValues()
                        }
                    }
                }){ Text("Убрать")}},
                title = { Text("Оставить введенные значения после сохранения файла")},
                text = {
                    Row {
                        Checkbox(
                            checked = !showLeaveValueAlertFlagSetting.value,
                            onCheckedChange = { // если галочка стоит
                                showMessage(context = context, message = "Можно изменить в настройках")
                                coroutineScope.launch {
                                    viewModel.booleanChanges(viewModel.showLeaveValueAlertFlagKey,!it) // Больше не спрашиваем перед сохраненением
                                }
                            }
                        )
                        Text("Больше не показывать это окно")
                    }
                }
            )
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 110.dp, bottom = 80.dp)
                .imePadding()
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
}

