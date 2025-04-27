package com.example.documenteditor.templatesFun

import com.example.documenteditor.getFile
import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.FileOutputStream

//Заявление на выдачу решения егоров
fun egorov(
    document: XWPFDocument,
    where: String, // Куда
    address : String, // адресс суда
    firstRole: String, // роль заявителя
    applicant: String, // заявитель
    applicantInit: String, // инициалы заявителя
    applicantInfo: String, // инфа о заявителе
    secondRole: String, // роль второй сторны
    second: String, // вторая сторона
    secondInfo: String, //Информация о второй стороне
    caseNumber: String, // номер дела
    text: String, //текст заявления
): XWPFDocument {
    //Словарь для замены
    val replace = mapOf(
        "ТЕКСТЗАЯВЛЕНИЯ" to text,
        "ЗИН" to applicantInit
    )

    // Замена по таблицам
    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = "$where \n $address"
    table1.getRow(2).getCell(0).text = "$firstRole: \n (ЗАЯВИТЕЛЬ)"
    table1.getRow(2).getCell(1).text = "$applicant, $applicantInfo"
    table1.getRow(4).getCell(0).text = "$secondRole:"
    table1.getRow(4).getCell(1).text = "$second \n $secondInfo"
    table1.getRow(6).getCell(1).text = caseNumber

    val table2 = document.tables[1]
    table2.getRow(0).getCell(1).text = "$where \n $address"
    table2.getRow(2).getCell(0).text = "$firstRole: \n (ЗАЯВИТЕЛЬ)"
    table2.getRow(2).getCell(1).text = "$applicant, $applicantInfo"
    table2.getRow(4).getCell(0).text = "$secondRole:"
    table2.getRow(4).getCell(1).text = "$second \n $secondInfo"
    table2.getRow(6).getCell(1).text = caseNumber

    // Замена по тексту
    textChange(document, replace)

    return document
}