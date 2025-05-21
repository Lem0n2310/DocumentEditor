package com.example.documenteditor.templatesFun

import com.example.documenteditor.functions.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument


fun incasso(
    document: XWPFDocument,
    where : String, // куда
    recover : String, // взыскатель
    recoverInit : String, // Инициалы взыскателя
    recoverInfo: String, // Инфа о взыскателе
    recoverRp :String,// ФИО взыскателя в родительном падеже
    debtor : String, // должник
    debtorInfo : String, // инфа о должнике
    listNumber : String, // номер листа
    caseNumber : String,// номер дела
    courtIssue : String, // каким судом выдан
    money : String, // Инфа о взыскиваемых средствах
    sumMoney : String, //Сумма Долга
    requisites: String,// Реквизиты
):XWPFDocument{
    // Словарь для замены
    val replace = mapOf(
        "НОМЕРЛИСТА" to listNumber,
        "НОМЕРДЕЛА" to caseNumber,
        "КАКИМСУДОМВЫДАН" to courtIssue,
        "ДОЛЖНИК" to debtor,
        "ИОД" to debtorInfo,
        "ВЗЫСКАТЕЛЬ" to recover,
        "ИОВ" to recoverInfo,
        "ИНФАОВЗЫСКИВАЕМЫХСРЕДСТВАХ" to money,
        "ВИН" to recoverInit,
        "СУММАДОЛГА" to sumMoney,
        "ВРП" to recoverRp
    )
    // Замена по таблицам
    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = "В $where"
    table1.getRow(2).getCell(1).text = "$recover, $recoverInfo. \n \n" +
            "Представители по доверенности Рябов Максим Николаевич (паспорт серия 65 22 № 609017 выдан 31.08.2022 г. ГУ МВД России по Свердловской области);\\n \\n" +
            "Адрес для направления корреспонденции:620033, г. Екатеринбург, ул. Черемуховая, д. 10 Тел. 8 912 220 78 48, Email: maxrabota@mail.ru"
    table1.getRow(4).getCell(1).text = "$debtor \n $debtorInfo"

    val table2 = document.tables[1]
    table2.getRow(0).getCell(0).text = requisites

    // Замена по тексту
    textChange(document, replace)

    return document
}

