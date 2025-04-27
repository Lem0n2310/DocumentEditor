
package com.example.documenteditor

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import javax.xml.transform.Templates
// Единственная Мэйн Активити
class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNav() // Навигация
        }
    }
}

@Composable
fun MyNav(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenRoute
    ){
        composable<MainScreenRoute>{
            MainScreen(navController) // Отрисовка главного экрана
        }

        composable<TemplatePickerRoute>{
            TemplatePicker(templates, navController) // Отрисовка экрана выбора шаблонов
        }
        composable<Screen2Route> {
            val title = it.toRoute<Screen2Route>().title
            Screen2(
                title = title,
                navController = navController
            )
        }
        composable<TemplateRoute> {// Отрисовка звполнения шаблонов
            val templateId = it.toRoute<TemplateRoute>().templateId
            val nameForDev = it.toRoute<TemplateRoute>().nameForDev
            Template(templateId = templateId , templates = templates, navController = navController, workFile = "templates/$nameForDev")
        }
    }
}

@Composable
private fun Screen2(title: String, navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Screen 2 $title")
            Button({navController.popBackStack()}) {
                Text("Go to screen 1")
            }
        }
    }
}



