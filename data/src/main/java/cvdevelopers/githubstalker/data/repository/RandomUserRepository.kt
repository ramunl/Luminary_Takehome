package cvdevelopers.githubstalker.data.repository

import cvdevelopers.githubstalker.data.model.Results
import cvdevelopers.githubstalker.utils.AppResult

interface RandomUserRepository {
    suspend fun getLocalPriorityResults(): AppResult<Results>
    suspend fun cleanCache()
}
