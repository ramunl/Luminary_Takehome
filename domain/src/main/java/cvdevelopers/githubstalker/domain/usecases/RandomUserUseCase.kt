package cvdevelopers.githubstalker.domain.usecases

import cvdevelopers.githubstalker.domain.entities.RandomUserEntity
import cvdevelopers.githubstalker.utils.AppResult

interface RandomUserUseCase {
    suspend fun getFreshRandomUsers(): AppResult<List<RandomUserEntity>>
    suspend fun getRandomUsers(): AppResult<List<RandomUserEntity>>
}
