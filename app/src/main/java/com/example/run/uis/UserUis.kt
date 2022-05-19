package com.example.run.uis

import android.widget.Chronometer
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.run.R
import com.example.run.appviewmodel.AppViewModel
import com.example.run.navigation.NavigationGraph
import com.example.run.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun MainApp(viewModel: AppViewModel,navcontroller:NavHostController,scope: CoroutineScope) {


            Box(modifier=Modifier.fillMaxSize()){
                Image(painter = painterResource(id = R.drawable.run), contentDescription =null,modifier=Modifier.matchParentSize() )
                MainApp(
                    isPlaying = viewModel.isPlaying,
                    seconds = viewModel.seconds,
                    minutes = viewModel.minutes,
                    hours = viewModel.hours,
                    onStart = { viewModel.start() },
                    onPause = { viewModel.pause() },
                    onStop = { viewModel.stop() }
                ,navcontroller = navcontroller,
                    scope = scope
                )
            }








}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MainApp(
    isPlaying: Boolean,
    seconds: String,
    minutes: String,
    hours: String,
    onStart: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    scope:CoroutineScope,navcontroller: NavHostController
) {

        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color.Blue)
                .verticalScroll(rememberScrollState())

        ) {


   Column( verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally,
   modifier=Modifier.padding(top=300.dp)) {


       Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
           Text(text = "hrs")
           Text(text = "min")
           Text(text = "sec")
       }
       Row {


           CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.h3) {
               Text(text = hours)
               Text(text = ":")
               Text(text = minutes)
               Text(text = ":")
               Text(text = seconds)


           }
       }
   }
            Spacer(modifier=Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically
            ) {

                    if (isPlaying)
                        IconButton(onClick = onPause) {
                            Icon(
                                painter = painterResource(id = R.drawable.stop),
                                contentDescription = "",modifier=Modifier.size(75.dp)
                            )
                        }
                    else
                        IconButton(onClick = onStart) {
                            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "",
                                modifier=Modifier.size(75.dp))
                        }

                IconButton(onClick = onStop) {
                    Icon(painter = painterResource(id = R.drawable.reset), contentDescription = "",
                        modifier=Modifier.size(75.dp))
                }

            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth().padding(top=16.dp),verticalAlignment = Alignment.CenterVertically){
                Button(onClick = {
                    scope.launch {
                        navcontroller.navigate(Screen.Home.route)
                    }
                }) {
                    Icon(Icons.Default.Home,contentDescription = null)

                }
                Button(onClick = {
                    scope.launch {
                        navcontroller.navigate(Screen.Home.route)
                    }
                }) {
                   Icon(painter = painterResource(id = R.drawable.save),contentDescription = null)

                }
            }

        }

    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {



}