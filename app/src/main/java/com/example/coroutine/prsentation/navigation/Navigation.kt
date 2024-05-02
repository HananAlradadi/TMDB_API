package com.example.coroutine.prsentation.navigation


sealed class Screenss (val rout : String){

    object lastBoarding : Screenss("lastBoarding")
    object seindBoarding : Screenss("seindBoarding")
    object firstBoarding : Screenss("firstBoarding")

}