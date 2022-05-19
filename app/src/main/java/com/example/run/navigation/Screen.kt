package com.example.run.navigation

sealed class Screen(val route:String){
    object Home:Screen(route = "home")
    object App:Screen(route = "app")
}
