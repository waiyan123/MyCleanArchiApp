package com.example.mycleanarchiapp.domain.repository

import com.example.mycleanarchiapp.data.remote.dto.CoinDetailDto
import com.example.mycleanarchiapp.data.remote.dto.CoinDto
import com.example.mycleanarchiapp.domain.model.Coin
import com.example.mycleanarchiapp.domain.model.CoinDetail

interface CoinRepository {

    suspend fun getCoins() : List<Coin>

    suspend fun getCoinById(coinId : String) : CoinDetail
}