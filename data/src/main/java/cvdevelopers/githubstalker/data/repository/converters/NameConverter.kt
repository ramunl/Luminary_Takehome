package cvdevelopers.githubstalker.data.repository.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cvdevelopers.githubstalker.data.model.Name


internal class NameConverter {
    @TypeConverter
    fun stringToName(json: String): Name? {
        val type = object : TypeToken<Name>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun nameToString(name: Name): String {
        val type = object : TypeToken<Name>() {}.type
        return Gson().toJson(name, type)
    }
}
