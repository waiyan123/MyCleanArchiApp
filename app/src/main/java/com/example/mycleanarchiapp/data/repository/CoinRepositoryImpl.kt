package com.example.mycleanarchiapp.data.repository

import androidx.room.RoomDatabase
import com.example.mycleanarchiapp.data.offline_database.MyRoomDatabase
import com.example.mycleanarchiapp.data.offline_database.entities.toCoinDto
import com.example.mycleanarchiapp.data.remote.CoinPaprikaApi
import com.example.mycleanarchiapp.data.remote.dto.CoinDetailDto
import com.example.mycleanarchiapp.data.remote.dto.CoinDto
import com.example.mycleanarchiapp.data.remote.dto.toCoin
import com.example.mycleanarchiapp.data.remote.dto.toCoinEntity
import com.example.mycleanarchiapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val roomDatabase: MyRoomDatabase
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {

        val coins = api.getCoins()
        roomDatabase.coinListDao().insertCoinsList(coins.map {
            it.toCoinEntity()
        })

        return roomDatabase.coinListDao().getCoinsList().map {
            it.toCoinDto()
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}