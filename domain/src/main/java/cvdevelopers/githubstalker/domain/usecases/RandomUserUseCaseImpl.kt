package cvdevelopers.githubstalker.domain.usecases

import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.domain.entities.RandomUserEntity
import cvdevelopers.githubstalker.domain.mapper.mapToEntity
import cvdevelopers.githubstalker.utils.AppResult

internal class RandomUserUseCaseImpl(private val randomUserRepository: RandomUserRepository) : RandomUserUseCase {
    override suspend fun getFreshRandomUsers(): AppResult<List<RandomUserEntity>> {
        randomUserRepository.cleanCache()
        return getRandomUsers();
    }

    override suspend fun getRandomUsers(): AppResult<List<RandomUserEntity>> {
        return with(randomUserRepository.getLocalPriorityResults()) {
            when (this) {
                is AppResult.Success -> {
                    AppResult.Success(mapToEntity(successData.results!!))
                }
                is AppResult.Error -> this
            }
        }
    }
}
