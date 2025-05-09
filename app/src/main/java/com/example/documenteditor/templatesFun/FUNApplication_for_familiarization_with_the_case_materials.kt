package com.example.documenteditor.templatesFun

import com.example.documenteditor.functions.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument

// Ходатайство об ознакомлении
fun ApplicationForFamiliarizationWithTheCaseMaterials(
    document: XWPFDocument,
    where: String, //куда
    role: String, // роль заявителя
    applicant: String, // заявитель
    applicantInit: String, // Инициалы заявителя
    caseNum: String,// номер дела
    phoneNum: String, // Номер телефона
    ): XWPFDocument {
    // Словарь для замены
    var replace = mapOf(
        "КУДА" to where,
        "ДЕЛО" to caseNum,
        "НОМЕР" to phoneNum,
        "ЗИН" to applicantInit
    )

    // Замена по таблицам
    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = where
    table1.getRow(2).getCell(0).text = role
    table1.getRow(2).getCell(1).text = applicant
    table1.getRow(4).getCell(1).text = caseNum

    val table2 = document.tables[1]
    table2.getRow(0).getCell(1).text = where
    table2.getRow(2).getCell(0).text = role
    table2.getRow(2).getCell(1).text = applicant
    table2.getRow(4).getCell(1).text = caseNum

    // Замена по тексту
    textChange(document, replace)

    return document
}

