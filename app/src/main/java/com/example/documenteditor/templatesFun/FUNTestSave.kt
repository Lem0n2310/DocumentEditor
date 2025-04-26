package com.example.documenteditor.templatesFun

import com.example.documenteditor.textChange
import org.apache.poi.xwpf.usermodel.XWPFDocument

fun test(
    document: XWPFDocument,
    field1: String,
    field2: String,
    field3: String,
    field4: String,
): XWPFDocument {
    val replace = mapOf(
        "ПОЛЕ1" to field1,
        "ПОЛЕ2" to field2,
        "ПОЛЕ3" to field3,
        "ПОЛЕ4" to field4,
    )

    val table1 = document.tables[0]
    table1.getRow(0).getCell(1).text = "Section 0, 1"
    table1.getRow(2).getCell(0).text = "Sectoin 2, 0"
    table1.getRow(2).getCell(1).text = "Sectoin 2, 1"

    textChange(document, replace)

    return document
}
