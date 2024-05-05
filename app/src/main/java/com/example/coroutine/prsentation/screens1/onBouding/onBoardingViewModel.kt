package com.example.coroutine.prsentation.screens1.onBouding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutine.domain.onBoarding.GetIsFirstTimeInDataStoreUseCase
import com.example.coroutine.domain.onBoarding.SaveIsFirstTimeInDataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class onBoardingViewModel @Inject constructor(
    private val saveIsFirstTimeInDataStoreUseCase: SaveIsFirstTimeInDataStoreUseCase,
    private val getIsFirstTimeInDataStoreUseCase: GetIsFirstTimeInDataStoreUseCase
) : ViewModel() {

    val onBoardingCompleted = MutableStateFlow(false)
    var startDestination : String = Screens.onBoarding.route
    init {
        getonBoardingState()
    }

    private fun getonBoardingState(){
     viewModelScope.launch {
         onBoardingCompleted.value = getIsFirstTimeInDataStoreUseCase().stateIn(viewModelScope).value
         startDestination = if (onBoardingCompleted.value) Screens.Home.route else Screens.onBoarding.route
     }
    }
    private fun saveonBoardingState(onBoardingPage : Boolean){

        viewModelScope.launch(Dispatchers.IO){
            saveIsFirstTimeInDataStoreUseCase(showTipsPage =  onBoardingPage)
        }
    }
    public fun startPage(): Boolean {
        return onBoardingCompleted.value
    }
}