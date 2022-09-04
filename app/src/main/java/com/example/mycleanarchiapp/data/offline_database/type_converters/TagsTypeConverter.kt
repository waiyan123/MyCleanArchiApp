package com.example.mycleanarchiapp.data.offline_database.type_converters

import androidx.room.TypeConverter
import com.example.mycleanarchiapp.data.remote.dto.Tag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagsTypeConverter {

    @TypeConverter
    fun toString(list : List<Tag>) :String {

        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json:String) : List<Tag>{

        val typeToken = object : TypeToken<List<Tag>>(){

        }.type
        return Gson().fromJson(json,typeToken)
    }
}