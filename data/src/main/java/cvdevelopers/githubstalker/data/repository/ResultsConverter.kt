package cvdevelopers.githubstalker.data.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cvdevelopers.githubstalker.data.model.Results


class ResultsConverter {
    @TypeConverter
    fun stringToResults(json: String): Results {
        val gson = Gson()
        val type = object : TypeToken<Results>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun resultsToString(list: Results): String {
        val gson = Gson()
        val type = object : TypeToken<Results>() {}.type
        return gson.toJson(list, type)
    }
}
