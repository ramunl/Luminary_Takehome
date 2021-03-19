package cvdevelopers.githubstalker.data.util

import android.util.Log
import com.google.gson.GsonBuilder
import cvdevelopers.githubstalker.utils.TAG
import retrofit2.Response
import java.io.IOException


object ApiErrorUtils {

    fun parseError(response: Response<*>): cvdevelopers.githubstalker.utils.APIError {

        val gson = GsonBuilder().create()
        val error: cvdevelopers.githubstalker.utils.APIError

        try {
            error = gson.fromJson(response.errorBody()?.string(), cvdevelopers.githubstalker.utils.APIError::class.java)
        } catch (e: IOException) {
            e.message?.let { Log.d(TAG, it) }
            return cvdevelopers.githubstalker.utils.APIError()
        }
        return error
    }

}