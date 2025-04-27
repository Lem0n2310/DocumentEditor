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
            DocumentField(label = "Информация о должнике"),
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
        nameForUser = "Ходатайство об ознакомлении",
        nameForDevelop = "RBA pattern.docx",
        fields = listOf(
            DocumentField(label = "Куда"),
            DocumentField(label = "Роль заявителя"),
            DocumentField(label = "Заявитель"),
            DocumentField(label = "Инициалы заявителя"),
            DocumentField(label = "Номер дела"),
            DocumentField(label = "Номер телефона"),
        )
    ),

    DocumentTemplate(
        id = 3,
        nameForUser = "Ходатайство о выдаче",
        nameForDevelop = "Mogil pattern.docx",
        fields = listOf(
            DocumentField(label = "Куда"),
            DocumentField(label = "Роль заявителя"),
            DocumentField(label = "Заявитель"),
            DocumentField(label = "Инициалы заявителя"),
            DocumentField(label = "Номер дела"),
            DocumentField(label = "О чем ходатайство"),
            DocumentField(label = "Текст ходатайства"),
        )
    ),

    DocumentTemplate(
        id = 4,
        nameForUser = "Заявление на выдачу решения егоров",
        nameForDevelop = "Egorov pattern.docx",
        fields = listOf(
            DocumentField(label = "Куда"),
            DocumentField(label = "Адресс суда"),
            DocumentField(label = "Роль заявителя"),
            DocumentField(label = "Заявитель"),
            DocumentField(label = "Инициалы заявителя"),
            DocumentField(label = "Информация о заявителе"),
            DocumentField(label = "Роль второй сторны"),
            DocumentField(label = "Вторая сторона"),
            DocumentField(label = "Информация о второй стороне"),
            DocumentField(label = "Номер дела"),
            DocumentField(label = "Текст заявления"),
        )
    ),

    DocumentTemplate(
        id = 5,
        nameForUser = "Ходатайство о проведении онлайн суда",
        nameForDevelop = "online pattern.docx",
        fields = listOf(
            DocumentField(label = "Куда"),
            DocumentField(label = "Роль заявителя"),
            DocumentField(label = "Заявитель"),
            DocumentField(label = "Инициалы заявителя"),
            DocumentField(label = "Информация о заявителе"),
            DocumentField(label = "Номер дела"),
            DocumentField(label = "Дата заседания"),
            DocumentField(label = "Суды для организации конфернеции"),
        )
    ),

    DocumentTemplate(
        id = 6,
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