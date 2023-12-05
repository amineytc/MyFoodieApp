package com.amineaytac.myfoodieapp.data.source.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnytoString(attribute:Any?):String{
        if(attribute==null)
            return ""
        return attribute as String
    }

    fun fromStringtoAny(attribute: String?):Any{
        if(attribute==null)
            return ""
        return attribute
    }
}