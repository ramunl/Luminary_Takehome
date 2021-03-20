package cvdevelopers.githubstalker.data.repository.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cvdevelopers.githubstalker.data.model.Picture


internal class PictureConverter {
    @TypeConverter
    fun stringToName(json: String): Picture? {
        val gson = Gson()
        val type = object : TypeToken<Picture>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nameToString(picture: Picture): String {
        val gson = Gson()
        val type = object : TypeToken<Picture>() {}.type
        return gson.toJson(picture, type)
    }
}
