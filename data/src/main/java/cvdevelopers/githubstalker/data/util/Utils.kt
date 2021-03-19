package cvdevelopers.githubstalker.data.util

import retrofit2.Response

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): cvdevelopers.githubstalker.utils.AppResult.Error {
        val error = ApiErrorUtils.parseError(resp)
        return cvdevelopers.githubstalker.utils.AppResult.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): cvdevelopers.githubstalker.utils.AppResult<T> {
        response.body()?.let {
            return cvdevelopers.githubstalker.utils.AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}