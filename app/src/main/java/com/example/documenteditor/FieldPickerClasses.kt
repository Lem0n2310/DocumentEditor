package com.example.documenteditor

import kotlinx.serialization.Serializable

// Класс описывающий поле
@Serializable
data class DocumentField(
    val label: String, // Название поля
)

// Класс описывающий шаблон документа
@Serializable
data class DocumentTemplate(
    val id: Int, // Номер шаблона
    val name: String, // Имя шаблона
    val fields: List<DocumentField> // Поля шаблона
)
