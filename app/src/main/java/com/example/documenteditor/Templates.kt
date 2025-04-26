package com.example.documenteditor

val templates = listOf(
    DocumentTemplate(
        id = 0, //инкассо
        nameForUser = "Инкассо",
        nameForDevelop = "Incasso pattern.docx",
        fields = listOf(
            DocumentField(label = "Куда"),
            DocumentField(label = "Взыскатель"),
            DocumentField(label = "Инициалы взыскателя"),
            DocumentField(label = "Информация о взыскателе"),
            DocumentField(label = "ФИО взыскателя в родительном падеже"),
            DocumentField(label = "Должник"),
            DocumentField(label = "Инфа о должнике"),
            DocumentField(label = "Номер листа"),
            DocumentField(label = "Номер дела"),
            DocumentField(label = "Каким судом выдан"),
            DocumentField(label = "Информация о взыскиваемых средствах"),
            DocumentField(label = "Сумма Долга"),
            DocumentField(label = "Реквизиты")
        )
    ),

    DocumentTemplate(
        id = 1, //заявление о возбуждении исполнительного производства
        nameForUser = "Заявление о возбуждении исполнительного производства",
        nameForDevelop = "IE pattern.docx",
        fields = listOf(
            DocumentField(label = "В какой суд"),
            DocumentField(label = "Взыскатель"),
            DocumentField(label = "Инициалы взыскателя"),
            DocumentField(label = "Информация о взыскателе"),
            DocumentField(label = "Должник"),
            DocumentField(label = "ФИО должника в родительном падеже"),
            DocumentField(label = "Информация о должнике"),
            DocumentField(label = "Инициалы должника"),
            DocumentField(label = "Получатель (по умолчанию взыскатель)"),
            DocumentField(label = "Дата вступления в законную силу"),
            DocumentField(label = "Дата выдачи исполнительного листа"),
            DocumentField(label = "Номер листа и дата"),
            DocumentField(label = "Информация о суде"),
            DocumentField(label = "Номер дела"),
            DocumentField(label = "Полная сумма долга"),
            DocumentField(label = "Сведения о требовании"),
            DocumentField(label = "Реквизиты")
        )
    ),

    DocumentTemplate(
        id = 2,
        nameForUser = "test",
        nameForDevelop = "Test.docx",
        fields = listOf(
            DocumentField(label = "name1"),
            DocumentField(label = "name2"),
            DocumentField(label = "name3"),
            DocumentField(label = "name4"),
        )
    ),


)