package com.example.documenteditor
import java.io.File

fun getFile(patternType: String, userSaveWay: String, name:String):String{
    val src = File("RES/$patternType")
    val dest = File("$userSaveWay/$name.docx")
    src.copyTo(dest)
    return "$userSaveWay/$name.docx"
}