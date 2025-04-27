package com.example.documenteditor

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.documenteditor.templatesFun.ApplicationForFamiliarizationWithTheCaseMaterials
import com.example.documenteditor.templatesFun.ApplicationForInitiationOfSoleProprietorship
import com.example.documenteditor.templatesFun.aftei
import com.example.documenteditor.templatesFun.egorov
import com.example.documenteditor.templatesFun.incasso
import com.example.documenteditor.templatesFun.oline
import org.apache.poi.xwpf.usermodel.XWPFDocument

class SaveViewModel: ViewModel() {
    fun save(
        context: Context,
        uri: Uri,
        selectedTemplate: DocumentTemplate,
        fieldValues: Map<String, String>,
        workFile: String,
    ){
        val templateDocument = XWPFDocument(context.assets.open(workFile))
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
                    debtorInfo = fieldValues["Информация о должнике"].toString(),
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
            2 -> {
                ApplicationForFamiliarizationWithTheCaseMaterials(
                    templateDocument,
                    fieldValues["Куда"].toString(),
                    fieldValues["Роль заявителя"].toString(),
                    fieldValues["Заявитель"].toString(),
                    fieldValues["Инициалы заявителя"].toString(),
                    fieldValues["Номер дела"].toString(),
                    fieldValues["Номер телефона"].toString(),
                )
            }
            3 -> {
                aftei(
                    templateDocument,
                    fieldValues["Куда"].toString(),
                    fieldValues["Роль заявителя"].toString(),
                    fieldValues["Заявитель"].toString(),
                    fieldValues["Инициалы заявителя"].toString(),
                    fieldValues["Номер дела"].toString(),
                    fieldValues["О чем ходатайство"].toString(),
                    fieldValues["Текст ходатайства"].toString(),
                )
            }
            4 -> {
                egorov(
                    templateDocument,
                    fieldValues["Куда"].toString(),
                    fieldValues["Адресс суда"].toString(),
                    fieldValues["Роль заявителя"].toString(),
                    fieldValues["Заявитель"].toString(),
                    fieldValues["Инициалы заявителя"].toString(),
                    fieldValues["Информация о заявителе"].toString(),
                    fieldValues["Роль второй сторны"].toString(),
                    fieldValues["Вторая сторона"].toString(),
                    fieldValues["Информация о второй стороне"].toString(),
                    fieldValues["Номер дела"].toString(),
                    fieldValues["Текст заявления"].toString(),
                )
            }
            5 -> {
                oline(
                    templateDocument,
                    fieldValues["Куда"].toString(),
                    fieldValues["Роль заявителя"].toString(),
                    fieldValues["Заявитель"].toString(),
                    fieldValues["Инициалы заявителя"].toString(),
                    fieldValues["Информация о заявителе"].toString(),
                    fieldValues["Номер дела"].toString(),
                    fieldValues["Дата заседания"].toString(),
                    fieldValues["Суды для организации конфернеции"].toString(),
                )
            }
            else -> {
                null
            }
        }

        context.contentResolver.openOutputStream(uri)?.use{outputStream ->
            document?.write(outputStream)
        }
    }
}