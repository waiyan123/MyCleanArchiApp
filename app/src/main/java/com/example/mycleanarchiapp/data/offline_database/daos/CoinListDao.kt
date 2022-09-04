package com.example.mycleanarchiapp.data.offline_database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycleanarchiapp.data.offline_database.entities.CoinEntity

@Dao
interface CoinListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinsList(coinsList : List<CoinEntity>)

    @Query("SELECT * FROM coin_table")
    suspend fun getCoinsList() : List<CoinEntity>

}