package com.example.mycleanarchiapp.data.repository

import android.util.Log
import androidx.room.RoomDatabase
import com.example.mycleanarchiapp.data.offline_database.MyRoomDatabase
import com.example.mycleanarchiapp.data.offline_database.entities.toCoin
import com.example.mycleanarchiapp.data.offline_database.entities.toCoinDto
import com.example.mycleanarchiapp.data.remote.CoinPaprikaApi
import com.example.mycleanarchiapp.data.remote.dto.*
import com.example.mycleanarchiapp.domain.model.Coin
import com.example.mycleanarchiapp.domain.model.CoinDetail
import com.example.mycleanarchiapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val roomDatabase: MyRoomDatabase
) : CoinRepository {

    override suspend fun getCoins(): List<Coin> {

        val coins = api.getCoins()
        roomDatabase.coinListDao().insertCoinsList(coins.map {
            it.toCoinEntity()
        })

        return roomDatabase.coinListDao().getCoinsList().map {
            it.toCoin()
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        return api.getCoinById(coinId).toCoinDetail()
    }

}