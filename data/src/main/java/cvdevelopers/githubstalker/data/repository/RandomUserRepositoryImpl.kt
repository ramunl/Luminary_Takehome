package cvdevelopers.githubstalker.data.repository

import android.content.Context
import android.util.Log
import cvdevelopers.githubstalker.data.model.RandomUser
import cvdevelopers.githubstalker.data.model.ResultsPage
import cvdevelopers.githubstalker.data.network.RandomUserApi
import cvdevelopers.githubstalker.data.util.Utils.handleApiError
import cvdevelopers.githubstalker.data.util.Utils.handleSuccess
import cvdevelopers.githubstalker.data.util.isOnline
import cvdevelopers.githubstalker.data.util.localStorageError
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


    private suspend fun getRemoteResults(): AppResult<ResultsPage> {
        Log.d(TAG, "getRemoteResults")
        return try {
            if (context.isOnline()) {
                val response = api.getResults()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) {
                            it.page = it.info.page
                            resultsDatabase.resultsDao.add(it) }
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

    /**
     * @return either list of random users or error message
     * @param fetchFromServerIfNeeded flag defines, do we need to hit remote server if local data doesn't exist ?
     */
    override suspend fun getLocalPriorityResults(fetchFromServerIfNeeded: Boolean): AppResult<List<RandomUser>> {
        Log.d(TAG, "getLocalPriorityResults")
        var appResult: AppResult<List<RandomUser>>? = null
        val resPages = mutableListOf<ResultsPage>()
        //firstly we read data from db
        val localPages = getFromCache()
        if (localPages?.isNotEmpty() == true) {
            //ok, the records exist, we read pages
            resPages.addAll(localPages)
        } else if (fetchFromServerIfNeeded) {
            //no cache - trying to fetch pages from server
            val remotePage = getRemoteResults()
            if (remotePage is AppResult.Success) {
                resPages.add(remotePage.successData)
            } else {
                //request failed, we save the error message
                appResult = remotePage as AppResult.Error
            }
        }
        //we need to merge all pages into a single list
        val randomUserListRes = mutableListOf<RandomUser>()
        if (resPages.isNotEmpty()) {
            for (page in resPages.iterator()) {
                page.results?.let { randomUserListRes.addAll(it) }
            }
        }
        return if (randomUserListRes.isNotEmpty()) {
            AppResult.Success(randomUserListRes)
        } else {
            //can it that we have pages saved, but all of them are empty?
            appResult ?: context.localStorageError()
        }
    }

    override suspend fun cleanCache() {
        Log.d(TAG, "cleanCache")
        return withContext(Dispatchers.IO) {
            resultsDatabase.clearAllTables()
        }
    }

    private suspend fun getFromCache(): List<ResultsPage>? {
        Log.d(TAG, "getFromCache")
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