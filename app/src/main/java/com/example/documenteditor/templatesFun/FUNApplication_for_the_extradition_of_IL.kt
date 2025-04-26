package com.example.documenteditor.templatesFun

import com.example.documenteditor.getFile
import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.FileOutputStream


fun aftei(
    document: XWPFDocument,
    where :String,
    role :String,
    applicant :String,
    caseNum :String,
    about :String,
    text :String,
    listApp :String,
    applicantInit :String,
): XWPFDocument {
    // Словарь для замены
    var replace = mapOf(
        "ОЧЕМ" to about,
        "ТЕКСТ" to text,
        "ЗИН" to applicantInit
    )
    // Замена по таблицам
    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = where
    table1.getRow(2).getCell(0).text = role
    table1.getRow(2).getCell(1).text = "$applicant \n \n 20033, г. Екатеринбург, ул. Черемуховая, д. 10 т.8-912-220-78-48, e-mail: maxrabota@mail.ru"
    table1.getRow(4).getCell(1).text = caseNum

    val table2 = document.tables[1]
    table2.getRow(0).getCell(1).text = where
    table2.getRow(2).getCell(0).text = role
    table2.getRow(2).getCell(1).text = "$applicant \n \n 20033, г. Екатеринбург, ул. Черемуховая, д. 10 т.8-912-220-78-48, e-mail: maxrabota@mail.ru"
    table2.getRow(4).getCell(1).text = caseNum

    // Замена по тексту
    textChange(document, replace)

    return document
}