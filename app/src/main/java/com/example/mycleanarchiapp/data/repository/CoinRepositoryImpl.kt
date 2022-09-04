package com.example.mycleanarchiapp.data.repository

import com.example.mycleanarchiapp.data.remote.CoinPaprikaApi
import com.example.mycleanarchiapp.data.remote.dto.CoinDetailDto
import com.example.mycleanarchiapp.data.remote.dto.CoinDto
import com.example.mycleanarchiapp.domain.repository.CoinRepository

class CoinRepositoryImpl(private val api: CoinPaprikaApi) : CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}