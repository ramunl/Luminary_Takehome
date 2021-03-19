package cvdevelopers.githubstalker.domain.di

import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCase
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCaseImpl
import org.koin.dsl.module

val userUseCaseModule = module {
    fun provideRandomUserUseCase(randomUserRepository: RandomUserRepository): RandomUserUseCase {
        return RandomUserUseCaseImpl(randomUserRepository)
    }
    single { provideRandomUserUseCase(get()) }
}