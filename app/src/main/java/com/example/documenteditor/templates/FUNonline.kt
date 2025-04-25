package com.example.documenteditor.templates

import com.example.documenteditor.getFile
import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.FileOutputStream

fun oline(
    userSaveWay : String,
    name: String,
    where : String,
    role : String,
    applicant : String,
    applicantInit: String,
    applicantInfo: String,
    caseNum: String,
    date: String,
    courtConnect:String
){
    val splitedCourtConnect = courtConnect.split(", ")

    //Создание файла
    val workFile = getFile("IE pattern.docx", userSaveWay = userSaveWay, name = name)

    //Словарь для замены
    val replace = mapOf(
        "ДАТА" to date,
        "ДАТА" to caseNum,
        "ЗИН" to applicantInit
    )

    // Замена по таблицам
    val document = XWPFDocument(FileInputStream(workFile))

    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = where
    table1.getRow(2).getCell(0).text = "$role \n (ЗАЯВИТЕЛЬ)"
    table1.getRow(2).getCell(1).text = "$applicant, $applicantInfo\n \n 20033, г. Екатеринбург, ул. Черемуховая, д. 10 т.8-912-220-78-48, e-mail: maxrabota@mail.ru"
    table1.getRow(6).getCell(1).text = caseNum

    val table2 = document.tables[1]
    for(court in splitedCourtConnect){
        table2.getRow(splitedCourtConnect.indexOf(court)-1)
            .getCell(1).text = court
    }

    // Замена по тексту
    textChange(document, replace)

    FileOutputStream(workFile).use{ fos ->
        document.write(fos)
    }

    document.close()
}