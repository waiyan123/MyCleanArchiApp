package com.example.mycleanarchiapp.data.offline_database.type_converters

import androidx.room.TypeConverter
import com.example.mycleanarchiapp.data.remote.dto.TeamMember
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TeamMembersTypeConverter {

    @TypeConverter
    fun toString(list : List<TeamMember>) :String {

        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json:String) : List<TeamMember>{

        val typeToken = object : TypeToken<List<TeamMember>>(){

        }.type
        return Gson().fromJson(json,typeToken)
    }
}