package com.example.mycleanarchiapp.domain.repository

import com.example.mycleanarchiapp.data.remote.dto.CoinDetailDto
import com.example.mycleanarchiapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinById(coinId : String) : CoinDetailDto
}