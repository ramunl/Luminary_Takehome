package cvdevelopers.githubstalker.data.util

import android.util.Log
import com.google.gson.GsonBuilder
import cvdevelopers.githubstalker.utils.APIError
import cvdevelopers.githubstalker.utils.TAG
import retrofit2.Response
import java.io.IOException


object ApiErrorUtils {

    fun parseError(response: Response<*>): APIError {
        val gson = GsonBuilder().create()
        val error: APIError

        try {
            error = gson.fromJson(response.errorBody()?.string(), APIError::class.java)
        } catch (e: IOException) {
            e.message?.let { Log.d(TAG, it) }
            return APIError()
        }
        return error
    }

}