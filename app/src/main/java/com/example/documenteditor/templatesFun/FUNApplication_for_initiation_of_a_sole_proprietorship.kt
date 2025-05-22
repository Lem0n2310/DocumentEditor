package com.example.documenteditor.templatesFun

import com.example.documenteditor.functions.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument

//Заявление о возбуждении ИП
fun ApplicationForInitiationOfSoleProprietorship(
    document: XWPFDocument,
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
): XWPFDocument{
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

    // Замена по таблицам
    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = where
    table1.getRow(2).getCell(1).text = "$recover \n $recoverInfo \n \n 620033, г. Екатеринбург, ул. Черемуховая, д. 10 Тел. 8 912 220 78 48, E – mail: maxrabota@mail.ru"
    table1.getRow(4).getCell(1).text = "$debtor, $debtorInfo"

    val table2 = document.tables[1]
    table2.getRow(0).getCell(0).text = requisites

    // Замена по тексту
    textChange(document, replace)

    return document
}
