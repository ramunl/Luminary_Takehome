package cvdevelopers.githubstalker.data.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cvdevelopers.githubstalker.data.model.RandomUser


class RandomUserConverter {
    @TypeConverter
    fun fromRandomUserList(userList: List<RandomUser?>?): String? {
        return userList?.let {
            val type = object : TypeToken<List<RandomUser?>?>() {}.type
            return Gson().toJson(it, type)
        }
    }

    @TypeConverter
    fun toRandomUserList(json: String?): List<RandomUser>? {
        return json?.let {
            val gson = Gson()
            val type = object : TypeToken<List<RandomUser?>?>() {}.type
            return gson.fromJson<List<RandomUser>>(it, type)
        }
    }
}