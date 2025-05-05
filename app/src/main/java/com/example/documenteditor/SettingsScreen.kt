package com.example.documenteditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController){
    val context = LocalContext.current.applicationContext

    val viewModel = viewModel<SettingDataStore>{
        SettingDataStore(context.dataStore)
    }

    val isSaveValue = viewModel.isSaveValue.collectAsState(true)
    val showAlertFlag = viewModel.showAlertFlag.collectAsState(true)
    val askAlert = viewModel.askAlert.collectAsState(true)

    val coroutineScope = rememberCoroutineScope()

    Row(Modifier.padding(vertical = 300.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("Спрашивать о сохранении введенных значений")
        Switch(
            checked = askAlert.value,
            onCheckedChange = {
                coroutineScope.launch {
                    viewModel.booleanChanges(viewModel.askAlertKey, it)
                    viewModel.booleanChanges(viewModel.showAlertFlagKey, it)
                    println(showAlertFlag)
                }
            }
        )
    }

    Row(Modifier.padding(vertical = 150.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("Сохранять введнные значения после сохранения?")
        Switch(
            enabled = !showAlertFlag.value,
            checked = isSaveValue.value,
            onCheckedChange = {
                coroutineScope.launch {
                    viewModel.booleanChanges(viewModel.saveValuesKey,it)
                }
            }
        )
    }

    TopAppBar(
        title = { Text("Настройки") },
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
    )
}

