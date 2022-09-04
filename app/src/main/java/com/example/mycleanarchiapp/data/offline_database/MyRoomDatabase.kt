package com.example.mycleanarchiapp.data.offline_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mycleanarchiapp.data.offline_database.daos.CoinListDao
import com.example.mycleanarchiapp.data.offline_database.entities.CoinDetailEntity
import com.example.mycleanarchiapp.data.offline_database.entities.CoinEntity
import com.example.mycleanarchiapp.data.offline_database.type_converters.TagsTypeConverter
import com.example.mycleanarchiapp.data.offline_database.type_converters.TeamMembersTypeConverter

@Database(entities = [CoinEntity::class,CoinDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(TagsTypeConverter::class,TeamMembersTypeConverter::class)
abstract class MyRoomDatabase : RoomDatabase(){

    abstract fun coinListDao(): CoinListDao
}