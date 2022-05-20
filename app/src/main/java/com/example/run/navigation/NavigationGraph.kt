package com.example.run.navigation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.run.R
import com.example.run.appviewmodel.AppViewModel
import com.example.run.uis.MainApp
import com.example.run.uis.SaveScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun NavigationGraph(navcontroller: NavHostController,scope:CoroutineScope,viewModel: AppViewModel) {

 NavHost(navController =navcontroller , startDestination =Screen.Home.route  ){
     composable(route = Screen.Home.route){
         HomeScreen(scope=scope,navcontroller = navcontroller)
     }
     composable(route = Screen.App.route){
         AppScreen(scope=scope,navcontroller = navcontroller,viewmodel = viewModel)
     }
     composable(route=Screen.Save.route){
         SaveScreen(viewModel=viewModel)
     }
 }

}

@ExperimentalTime
@Composable
fun SaveScreen(viewModel: AppViewModel) {
    SaveScreens(viewModel =viewModel )
}

@ExperimentalTime
@Composable
fun AppScreen(scope: CoroutineScope, navcontroller: NavHostController,viewmodel:AppViewModel){

MainApp(viewModel = viewmodel, navcontroller = navcontroller, scope =scope )
}

@Composable
fun HomeScreen(scope:CoroutineScope,navcontroller: NavHostController) {
    Column(modifier=Modifier.fillMaxSize()){
        Box(modifier= Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .verticalScroll(
                rememberScrollState()
            )){
            Image(painter = painterResource(id = R.drawable.run), contentDescription =null,modifier=Modifier.matchParentSize() )
            OutlinedButton(onClick = {
                scope.launch {
                    navcontroller.navigate(Screen.App.route)
                }
            },modifier= Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                ,shape = RoundedCornerShape(15.dp)) {
                Text(text="Start")

            }
            Text(text="Runner APP ",style = TextStyle(fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace),modifier=Modifier.align(Alignment.TopCenter))

        }

    }
}
