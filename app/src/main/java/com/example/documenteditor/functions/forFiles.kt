package com.example.documenteditor.functions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast


fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val scheme = uri.scheme

    if (scheme == "content") {
        val cursor: Cursor? = context.contentResolver.query(
            uri,
            arrayOf(OpenableColumns.DISPLAY_NAME),
            null,
            null,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index != -1) {
                    fileName = it.getString(index)
                }
            }
        }
    }

    // Если не удалось получить через ContentResolver или это file Uri
    if (fileName == null) {
        fileName = uri.lastPathSegment
        // Удаляем путь к директории если он есть
        val split = fileName?.split("/")
        if (split != null && split.isNotEmpty()) {
            fileName = split.last()
        }
    }

    return fileName
}

fun openDocxFromUri(context: Context, uri: Uri) {
    try {
        // Создаем Intent для просмотра файла
        val intent = Intent(Intent.ACTION_VIEW).apply {
            // Устанавливаем MIME-тип для DOCX
            setDataAndType(uri, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")

            // Даем временное разрешение на чтение
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        // Проверяем, есть ли приложение для обработки этого Intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            showError(context, "Нет приложения для открытия DOCX")
        }
    } catch (e: ActivityNotFoundException) {
        showError(context, "Приложение для DOCX не найдено")
    } catch (e: SecurityException) {
        showError(context, "Нет прав доступа к файлу")
    } catch (e: Exception) {
        showError(context, "Ошибка: ${e.localizedMessage}")
    }
}

private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
