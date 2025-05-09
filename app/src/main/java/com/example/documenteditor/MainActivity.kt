
package com.example.documenteditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.documenteditor.ClassesViewModels.MainScreenRoute
import com.example.documenteditor.ClassesViewModels.Screen2Route
import com.example.documenteditor.ClassesViewModels.SettingsScreenRoute
import com.example.documenteditor.ClassesViewModels.TemplatePickerRoute
import com.example.documenteditor.ClassesViewModels.TemplateRoute
import com.example.documenteditor.ClassesViewModels.templates
import com.example.documenteditor.ComposeFun.MainScreen
import com.example.documenteditor.ComposeFun.RecentDocs
import com.example.documenteditor.ComposeFun.SettingsScreen
import com.example.documenteditor.ComposeFun.Template
import com.example.documenteditor.ComposeFun.TemplatePicker


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
            RecentDocs(
                navController = navController
            )
        }
        composable<SettingsScreenRoute>{
            SettingsScreen(navController)
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



