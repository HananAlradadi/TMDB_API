package com.example.coroutine.domain.onBoarding

import com.example.coroutine.data.dataStor.MovieAppDataStor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveIsFirstTimeInDataStoreUseCase @Inject constructor(
    private val dataStor: MovieAppDataStor
) {

    suspend operator fun invoke(showTipsPage : Boolean)
    {
        dataStor.saveonBoardingSate(showTipsPage = showTipsPage)
    }
}