package com.example.documenteditor.ClassesViewModels

import kotlinx.serialization.Serializable
// Классы для нава
@Serializable
object MainScreenRoute //главный экран

@Serializable
data class Screen2Route(val title: String)

@Serializable
object TemplatePickerRoute // Выбор шаблона

@Serializable
data class TemplateRoute(val templateId: Int, val nameForDev: String) // Заполнение шаблона

@Serializable
object SettingsScreenRoute // Экран настроек