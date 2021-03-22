package cvdevelopers.githubstalker.domain.usecases

import android.content.Context
import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.domain.entities.RandomUserEntity
import cvdevelopers.githubstalker.domain.mapper.mapToEntity
import cvdevelopers.githubstalker.utils.AppResult
import cvdevelopers.githubstalker.utils.isFirstRequestSent
import cvdevelopers.githubstalker.utils.setIsFirstRequestSent

internal class RandomUserUseCaseImpl(private val context: Context, private val randomUserRepository: RandomUserRepository) : RandomUserUseCase {
    override suspend fun getFreshRandomUsers(): AppResult<List<RandomUserEntity>> {
        randomUserRepository.cleanCache()
        return getRandomUsers(true);
    }

    /**
     * The method return data either from local or remote storage
     * According to the task description
     * it doesn't fetch data from a server if it is not a first launch
     */
    override suspend fun getRandomUsers(): AppResult<List<RandomUserEntity>> {
        return getRandomUsers(!context.isFirstRequestSent())
    }


    private suspend fun getRandomUsers(fetchFromServer: Boolean): AppResult<List<RandomUserEntity>> {
        return with(randomUserRepository.getLocalPriorityResults(fetchFromServer)) {
            when (this) {
                is AppResult.Success -> {
                    if (!context.isFirstRequestSent()) {
                        context.setIsFirstRequestSent(true)
                    }
                    AppResult.Success(mapToEntity(successData))
                }
                is AppResult.Error -> this
            }
        }
    }
}
