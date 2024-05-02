package com.example.coroutine.prsentation.screens1.onBouding

sealed class Screens : Destination {
    object onBoarding : Screens() {
        override val route: String = "OnBoarding"
    }

    object PopularMovie : Screens() {
        override val route: String = "PopularMovie"
    }
}

interface Destination {
    val route: String
}