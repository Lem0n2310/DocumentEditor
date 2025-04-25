package com.example.documenteditor.templates

import com.example.documenteditor.getFile
import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.FileOutputStream


fun incasso(
    userSaveWay: String,
    name:String,
    where : String,
    recover : String,
    recoverInit : String,
    recoverInfo: String,
    debtor : String,
    debtorInfo : String,
    listNumber : String,
    caseNumber : String,
    courtIssue : String,
    money : String,
    sumMoney : String,
){
    // Создание файла
    val workFile = getFile("IE pattern.docx", userSaveWay = userSaveWay, name = name)

    // Словарь для замены
    val replace = mapOf(
        "НОМЕРЛИСТА" to listNumber,
        "НОМЕРДЕЛА" to caseNumber,
        "КАКИМСУДОМВЫДАН" to courtIssue,
        "ДОЛЖНИК" to debtor,
        "ИОД" to debtorInfo,
        "ВЗЫСКАТЕЛЬ" to recover,
        "ИОВ" to recoverInfo,
        "ИНФАОВЗЫСКИВАЕМЫХСРЕДСТВАХ" to recoverInit,
        "ВИН" to money,
        "СУММАДОЛГА" to sumMoney
    )

    // Замена по таблицам
    val document = XWPFDocument(FileInputStream(workFile))

    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = "В $where"
    table1.getRow(2).getCell(0).text = "$recover, $recoverInfo. \n \n" +
            "Представители по доверенности Рябов Максим Николаевич (паспорт серия 65 22 № 609017 выдан 31.08.2022 г. ГУ МВД России по Свердловской области);\\n \\n" +
            "Адрес для направления корреспонденции:620033, г. Екатеринбург, ул. Черемуховая, д. 10 Тел. 8 912 220 78 48, Email: maxrabota@mail.ru"
    table1.getRow(2).getCell(1).text = "$debtor \n $debtorInfo"

    // Замена по тексту
    textChange(document, replace)

    FileOutputStream(workFile).use{ fos ->
        document.write(fos)
    }

    document.close()
}

