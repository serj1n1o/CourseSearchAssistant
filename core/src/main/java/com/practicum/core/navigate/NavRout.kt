package com.practicum.core.navigate

sealed class NavRout(val rout: String) {

    data object Home : NavRout("myApp://home")
    data object Details : NavRout("myApp://details")
}