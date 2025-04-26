package com.example.documenteditor.templatesFun

val document = when(selectedTemplate.id){
    0 -> {
        incasso(
            document = templateDocument,
            where= fieldValues["Куда"].toString(),
            recover = fieldValues["Взыскатель"].toString(),
            recoverInit = fieldValues["Инициалы взыскателя"].toString(),
            recoverInfo = fieldValues["Информация о взыскателе"].toString(),
            recoverRp = fieldValues["ФИО взыскателя в родительном падеже"].toString(),
            debtor = fieldValues["Должник"].toString(),
            debtorInfo = fieldValues["Инфа о должнике"].toString(),
            listNumber = fieldValues["Номер листа"].toString(),
            caseNumber = fieldValues["Номер дела"].toString(),
            courtIssue= fieldValues["Каким судом выдан"].toString(),
            money = fieldValues["Информация о взыскиваемых средствах"].toString(),
            sumMoney = fieldValues["Сумма Долга"].toString(),
            requisites =fieldValues["Реквизиты"].toString()
        )
    }
    1 -> {
        ApplicationForInitiationOfSoleProprietorship(
            templateDocument,
            fieldValues["В какой суд"].toString(),
            fieldValues["Взыскатель"].toString(),
            fieldValues["Инициалы взыскателя"].toString(),
            fieldValues["Информация о взыскателе"].toString(),
            fieldValues["Должник"].toString(),
            fieldValues["ФИО должника в родительном падеже"].toString(),
            fieldValues["Информация о должнике"].toString(),
            fieldValues["Инициалы должника"].toString(),
            fieldValues["Получатель (по умолчанию взыскатель)"].toString(),
            fieldValues["Дата вступления в законную силу"].toString(),
            fieldValues["Дата выдачи исполнительного листа"].toString(),
            fieldValues["Номер листа и дата"].toString(),
            fieldValues["Информация о суде"].toString(),
            fieldValues["Номер дела"].toString(),
            fieldValues["Полная сумма долга"].toString(),
            fieldValues["Сведения о требовании"].toString(),
            fieldValues["Реквизиты"].toString(),
        )
    }