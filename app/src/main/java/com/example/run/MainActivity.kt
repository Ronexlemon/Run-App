package com.example.run

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.run.appviewmodel.AppViewModel
import com.example.run.navigation.NavigationGraph
import com.example.run.ui.theme.RunTheme
import com.example.run.uis.MainApp
import kotlin.time.ExperimentalTime
@ExperimentalTime
class MainActivity : ComponentActivity() {

    private val viewmodel:AppViewModel  by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val navcontroller = rememberNavController()
            val scaffoldstate = rememberScaffoldState()
            RunTheme {
                // A surface container using the 'background' color from the theme

//                    MainApp(viewModel = viewmodel)
                Scaffold (
                    scaffoldState =scaffoldstate ,content = {
                    NavigationGraph(navcontroller =navcontroller , scope = scope, viewModel = viewmodel)
                })



                }
            }
        }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RunTheme {
        Greeting("Android")
    }
}