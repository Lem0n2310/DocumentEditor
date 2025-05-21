package com.example.documenteditor.functions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

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



fun openDocxFile(context: Context, filePath: String) {
    val file = File(filePath)

    // Проверка существования файла
    if (!file.exists()) {
        showError(context, "Файл не найден")
        return
    }

    // Получение URI через FileProvider
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )

    // Создание Intent
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    // Попытка запуска
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        showError(context, "Нет приложения для открытия DOCX")
    } catch (e: Exception) {
        showError(context, "Ошибка: ${e.localizedMessage}")
    }
}

private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}