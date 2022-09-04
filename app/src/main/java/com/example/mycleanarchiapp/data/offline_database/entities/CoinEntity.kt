package com.example.mycleanarchiapp.data.offline_database.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycleanarchiapp.data.remote.dto.CoinDto

@Entity(tableName = "coin_table")
data class CoinEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
    @ColumnInfo(name = "is_new")
    val isNew : Boolean,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "type")
    val type : String

)
fun CoinEntity.toCoinDto() : CoinDto {
    return CoinDto(
        id = id,
        isActive = isActive,
        isNew = isNew,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type
    )
}