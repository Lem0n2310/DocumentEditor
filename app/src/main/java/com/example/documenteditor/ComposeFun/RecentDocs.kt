package com.example.documenteditor.ComposeFun

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController


@Composable
fun RecentDocs(navController: NavHostController){
    val stub = remember {
        mutableStateMapOf(1 to "path1", 2 to "path2", 3 to "path3", 4 to "path4", 5 to "path5", 6 to "path6", 7 to "path7", 8 to "path8", 9 to "path9", 10 to "path10", 11 to "path11", 12 to "path12", 13 to "path13", 14 to "path14", 15 to "path15", 16 to "path16", 17 to "path17", 18 to "path18", 19 to "path19", 20 to "path20", 21 to "path21")
    }
    val keys = stub.keys
    val context = LocalContext.current
    val recentDocs = context.getSharedPreferences("recentDocs", MODE_PRIVATE)
    


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
                item {
                    keys.forEach { key ->
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                showMessage(stub[key].toString(), context)
                            }
                        ) {
                            Text(stub[key].toString())
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

                Text("Go to screen 1")
            }
        }
    }



}


fun showMessage(message: String, context: Context){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}
