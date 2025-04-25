package com.example.documenteditor

import kotlinx.serialization.Serializable
// Классы для нава
@Serializable
object MainScreenRoute //главный экран

@Serializable
data class Screen2Route(val title: String)

@Serializable
object TemplatePickerRoute // Выбор шаблона

@Serializable
data class TemplateRoute(val templateId: Int) // Заполнение шаблона