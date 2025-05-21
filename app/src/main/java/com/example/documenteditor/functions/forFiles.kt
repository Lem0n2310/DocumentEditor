package com.example.documenteditor.functions

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns

fun getFilePath(context: Context, uri: Uri): String? {
    var cursor: Cursor? = null
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    return try {
        cursor = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.getString(columnIndex)
            } else null
        }
    } catch (e: Exception) {
        null
    }
}


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