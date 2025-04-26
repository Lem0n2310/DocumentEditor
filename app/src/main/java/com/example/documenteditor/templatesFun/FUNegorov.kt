package com.example.documenteditor.templatesFun

import com.example.documenteditor.getFile
import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.FileOutputStream

fun egorov(
    userSaveWay: String,
    name: String,
    where: String,
    address : String,
    firstRole: String,
    applicant: String,
    applicantInit: String,
    applicantInfo: String,
    secondRole: String,
    second: String,
    secondInfo: String,
    caseNumber: String,
    text: String,
){
    // Создание файла
    val workFile = getFile("IE pattern.docx", userSaveWay = userSaveWay, name = name)

    //Словарь для замены
    val replace = mapOf(
        "ТЕКСТЗАЯВЛЕНИЯ" to text,
        "ЗИН" to applicantInit
    )

    //Замена по таблицам
    val document = XWPFDocument(FileInputStream(workFile))

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

    FileOutputStream(workFile).use{ fos ->
        document.write(fos)
    }

    document.close()
}