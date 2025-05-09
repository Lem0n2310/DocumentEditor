package com.example.documenteditor.ComposeFun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.documenteditor.ClassesViewModels.Screen2Route
import com.example.documenteditor.ClassesViewModels.SettingsScreenRoute
import com.example.documenteditor.ClassesViewModels.TemplatePickerRoute
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff9DA7E8)),
        contentAlignment = Alignment.Center // Бокс на весь экран, контент по центру
    ){
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet{//Боковое окно
                    Scaffold(
                        bottomBar = {
                            BottomAppBar {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    IconButton({navController.navigate(SettingsScreenRoute)}) {
                                        Icon(
                                            Icons.Default.Settings,
                                            contentDescription = null,
                                            tint = Color.Blue
                                        )
                                    }
                                    Text("Настройки")
                                }
                            }
                        },
                        content ={}// Потом добавим Recent docs
                    )
                }
            },
            content={
                IconButton(onClick = {scope.launch {drawerState.open()}},
                    content = { Icon(Icons.Filled.Menu, "Меню") },
                    modifier = Modifier.padding(start = 15.dp, top = 50.dp)
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // Бокс на весь экран, контент по центру
                ) {
                    // Три кнопки в колонке
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { navController.navigate(TemplatePickerRoute) },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text("Создать документ по шаблону")
                        }

                        Button(
                            { navController.navigate(Screen2Route("my title")) },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text("Создать шаблон")
                        }

                        Button(
                            { navController.navigate(Screen2Route("my title")) },
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
        )
    }
}