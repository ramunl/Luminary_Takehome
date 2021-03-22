package cvdevelopers.githubstalker.data.repository.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cvdevelopers.githubstalker.data.model.PageInfo


internal class InfoConverter {
    @TypeConverter
    fun stringToInfo(json: String): PageInfo? {
        val gson = Gson()
        val type = object : TypeToken<PageInfo>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun infoToString(picture: PageInfo): String {
        val gson = Gson()
        val type = object : TypeToken<PageInfo>() {}.type
        return gson.toJson(picture, type)
    }
}
