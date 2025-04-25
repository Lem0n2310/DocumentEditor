package com.example.documenteditor.templates

import com.example.documenteditor.getFile
import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.FileOutputStream

//Заявление о возбуждении ИП
fun ApplicationForInitiationOfSoleProprietorship(
    userSaveWay: String,// Пусть сохранения
    name:String,//  Имя файла
    where :String, // В какой суд
    recover :String,//  ФИО взыскателя
    recoverInit :String,// Инициалы взыскателя
    recoverInfo :String,//  Инфа о взыскателе
    debtor :String,//  ФИО должника
    debtorRp :String,// ФИО должника в родительном падеже
    debtorInfo :String,// Инфа о должнике
    debtorInit :String,// Инициалы должника
    pol :String,//  Получатель (по умолчанию взыскатель)
    dateVz :String,// Дата вступления в законную силу
    dateVil :String,// Дата выдачи исполнительного листа
    listNumDate :String,// Номер листа и дата
    courtInfo :String,// Инфа о суде
    caseNum :String,//  Номер дела
    fulMoney :String,// Полная сумма долга
    infoRequire :String,// Сведения о требовании
    requisites: String,// Реквизиты
){


    // Создание файла
    val workFile = getFile("IE pattern.docx", userSaveWay = userSaveWay, name = name)

    val req = mutableMapOf(
        "bank" to "shit",
        "valuta" to "ruble",
        "doljnik" to "xyesos"
    )

    // Словарь для замены
    val replace = mapOf(
        "СУД" to  courtInfo,
        "НОМЕР ДЕЛА" to  caseNum,
        "ДВЗ" to  dateVz,
        "ДВИЛ" to  dateVil,
        "НОМЕРЛИСТАиДАТА" to  listNumDate,
        "ДОЛЖНИК" to  debtor,
        "РП" to  debtorRp,
        "ДИН" to  debtorInit,
        "ПСД" to  fulMoney,
        "СОТ" to  infoRequire,
        "ВЗЫСКАТЕЛЬ" to recover,
        "ВИН" to  recoverInit,
        "ИОВ" to  recoverInfo,
        "ИОД" to  debtorInfo,
        "ПОЛУЧАТЕЛЬ" to  pol
    )

    val document = XWPFDocument(FileInputStream(workFile))

    // Замена по таблицам
    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = where
    table1.getRow(2).getCell(1).text = "$recover \n $recoverInfo \n \n 620033, г. Екатеринбург, ул. Черемуховая, д. 10 Тел. 8 912 220 78 48, E – mail: maxrabota@mail.ru"
    table1.getRow(4).getCell(1).text = "$debtor, $debtorInfo"

    val table2 = document.tables[1]
    req.entries.forEachIndexed {
        i, (key, value) -> val row = table2.getRow(i)
        row.getCell(0).text = key
        row.getCell(1).text = value
    }

    // Замена по тексту
    textChange(document, replace)

    FileOutputStream(workFile).use{fos ->
        document.write(fos)
    }

    document.close()
}
