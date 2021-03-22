package cvdevelopers.githubstalker.data.repository

import cvdevelopers.githubstalker.data.model.RandomUser
import cvdevelopers.githubstalker.utils.AppResult

interface RandomUserRepository {
    suspend fun getLocalPriorityResults(fetchFromServerIfNeeded: Boolean): AppResult<List<RandomUser>>
    suspend fun cleanCache()
}
