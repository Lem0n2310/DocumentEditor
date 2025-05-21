package com.example.documenteditor.ComposeFun

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.LogPrinter
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.documenteditor.functions.openDocxFile
import database.Document
import database.DocumentDao
import database.DocumentViewModel


@Composable
fun RecentDocs(navController: NavHostController){

    // Константы
    val context = LocalContext.current
    val mDocumentViewModel = viewModel<DocumentViewModel>()
    val lifecycle = LocalLifecycleOwner.current

    // Передача данных из дб
    val livedata = mDocumentViewModel.readAllData
    val listSize = livedata.value?.size
    var documentList: List<Document> = emptyList()
    livedata.observe(lifecycle) { list ->
        documentList = livedata.value ?: emptyList()
    }


    Box(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = listSize?.toInt() ?: 0) {
                    documentList.forEach { document ->
                        val name = document.name
                        val path = document.path
                        val type = document.type
                        Column {
                            Button(
                                modifier = Modifier,
                                onClick = {
                                    openDocxFile(context, path)
                                }
                            ) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(name)
                                    Text(type, fontSize = 12.sp)
                                }
                            }
                            Text("", fontSize = 3.sp)
                        }
                    }
                }
            }


        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    alignment = Alignment.BottomCenter
                )
                .padding(bottom = 50.dp),
            contentAlignment = Alignment.Center,

            ) {
            Button(
                { navController.popBackStack() }
            ) {

                Text("Назад")
            }
        }
    }

}




fun showMessage(message: String, context: Context){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}
