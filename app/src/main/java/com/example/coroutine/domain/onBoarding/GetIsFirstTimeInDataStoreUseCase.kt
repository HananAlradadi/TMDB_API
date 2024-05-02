package com.example.coroutine.domain.onBoarding

import com.example.coroutine.data.dataStor.MovieAppDataStor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsFirstTimeInDataStoreUseCase @Inject constructor(
    private val dataStor: MovieAppDataStor
)  {
     operator fun invoke(): Flow<Boolean> {
        return dataStor.readonBoardingSate()
    }
}