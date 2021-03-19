package cvdevelopers.githubstalker.data.repository

import android.content.Context
import android.util.Log
import cvdevelopers.githubstalker.data.model.Results
import cvdevelopers.githubstalker.data.network.RandomUserApi
import cvdevelopers.githubstalker.data.util.NetworkManager.isOnline
import cvdevelopers.githubstalker.data.util.Utils.handleApiError
import cvdevelopers.githubstalker.data.util.Utils.handleSuccess
import cvdevelopers.githubstalker.data.util.noNetworkConnectivityError
import cvdevelopers.githubstalker.utils.AppResult
import cvdevelopers.githubstalker.utils.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RandomUserRepositoryImpl(
        private val api: RandomUserApi,
        private val context: Context,
        private val resultsDatabase: ResultsDatabase
) : RandomUserRepository {

    private suspend fun getLocalResults(): AppResult<Results> {
        //check in db if the data exists
        val data = getFromCache()
        return if (data?.results?.isNotEmpty() == true) {
            Log.d(TAG, "from db")
            AppResult.Success(data)
        } else {
            context.noNetworkConnectivityError()
        }
    }

    private suspend fun getRemoteResults(): AppResult<Results> {
        return try {
            if (isOnline(context)) {
                val response = api.getResults()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { resultsDatabase.resultsDao.add(it) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } else {
                context.noNetworkConnectivityError()
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getLocalPriorityResults(): AppResult<Results> {
        val data = getFromCache()
        return if (data?.results?.isNotEmpty() == true) {
            AppResult.Success(data)
        } else {
            return getRemoteResults()
        }
    }

    override suspend fun cleanCache() {
        return withContext(Dispatchers.IO) {
            resultsDatabase.clearAllTables()
        }
    }

    private suspend fun getFromCache(): Results? {
        return withContext(Dispatchers.IO) {
            try {
                resultsDatabase.resultsDao.findAll()
            } catch (e: Exception) {
                Log.e(TAG, e.stackTraceToString());
                null
            }
        }
    }
}