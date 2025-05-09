package com.example.documenteditor.ComposeFun

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.navigation.NavController
import com.example.documenteditor.ClassesViewModels.SettingDataStore
import com.example.documenteditor.ClassesViewModels.dataStore
import com.example.documenteditor.functions.textToShow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext

    val viewModel = viewModel<SettingDataStore> {
        SettingDataStore(context.dataStore)
    }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false)} // флаг отображения нижнего меню
    val isSaveValue = viewModel.isSaveValue.collectAsState(true) // Сохранять значения внутри полей
    val showLeaveValueAlertFlagSetting = viewModel.showLeaveValueAlertFlag.collectAsState(true) // Показывать окно сохранения введенных значений (для отображения вообще)
    val showCheckValuesFlagSetting = viewModel.showCheckValuesFlag.collectAsState(true) // Показывть окно проверки заполнения данных (вообще)

    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.padding(vertical = 160.dp)) {
        Row(Modifier //настройка проверки значений
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    coroutineScope.launch {
                        viewModel.booleanChanges(
                            viewModel.showCheckValuesFlagKey,
                            !showCheckValuesFlagSetting.value
                        )
                    }
                }
            ), verticalAlignment = Alignment.CenterVertically) {
            Text("Проверять заполнение всех данных перед сохранением?")
            Switch(
                checked = showCheckValuesFlagSetting.value,
                onCheckedChange = {
                    coroutineScope.launch {
                        viewModel.booleanChanges(viewModel.showCheckValuesFlagKey, it)
                    }
                }
            )
        }

        Row(Modifier.padding(vertical = 8.dp).clickable(onClick = {showBottomSheet = true})){
            Text("Данные после сохранения файла") // Настройка сохранения значений
            Text(textToShow(showLeaveValueAlertFlagSetting.value, isSaveValue.value), modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
    TopAppBar(
        title = { Text("Настройки") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xff8192fe),
            titleContentColor = Color.White,
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
        },
    )

    if(showBottomSheet){
        ModalBottomSheet( // менюшка настроек
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                Text("Данные после сохранения файла")

                TextButton(onClick = {
                    coroutineScope.launch {
                        viewModel.booleanChanges(viewModel.showLeaveValueAlertFlagKey, true)
                        showBottomSheet = false
                    }
                }) { Text("Спрашивать")}
                TextButton(onClick = {
                    coroutineScope.launch {
                        viewModel.booleanChanges(viewModel.saveValuesKey, true)
                        viewModel.booleanChanges(viewModel.showLeaveValueAlertFlagKey, false)
                        showBottomSheet = false
                    }
                }) { Text("Сохранять")}
                TextButton(onClick = {
                    coroutineScope.launch {
                        viewModel.booleanChanges(viewModel.saveValuesKey, false)
                        viewModel.booleanChanges(viewModel.showLeaveValueAlertFlagKey, false)
                        showBottomSheet = false
                    }
                }) { Text("Не сохранять")}
            }
        }
    }
}

