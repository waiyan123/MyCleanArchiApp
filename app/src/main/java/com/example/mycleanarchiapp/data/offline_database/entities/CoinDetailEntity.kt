package com.example.mycleanarchiapp.data.offline_database.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycleanarchiapp.data.remote.dto.Links
import com.example.mycleanarchiapp.data.remote.dto.LinksExtended
import com.example.mycleanarchiapp.data.remote.dto.Tag
import com.example.mycleanarchiapp.data.remote.dto.TeamMember

@Entity(tableName = "coin_detail_table")
data class CoinDetailEntity(

    @PrimaryKey
    @ColumnInfo(name ="id")
    val id: String,

    @ColumnInfo(name ="description")
    val description: String,
    @ColumnInfo(name ="is_active")
    val isActive: Boolean,
    @ColumnInfo(name ="name")
    val name: String,
    @ColumnInfo(name ="rank")
    val rank: Int,
    @ColumnInfo(name ="symbol")
    val symbol: String,
    @ColumnInfo(name ="tags")
    val tags: List<Tag>,
    @ColumnInfo(name ="team")
    val team: List<TeamMember>

)