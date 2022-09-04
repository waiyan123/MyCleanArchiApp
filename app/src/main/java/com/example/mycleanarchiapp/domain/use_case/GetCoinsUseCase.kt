package com.example.mycleanarchiapp.domain.use_case

import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.data.remote.dto.toCoin
import com.example.mycleanarchiapp.domain.model.Coin
import com.example.mycleanarchiapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = coinRepository.getCoins()
            emit(Resource.Success(coins))
        }
        catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
        catch (e : IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
        }
    }
}