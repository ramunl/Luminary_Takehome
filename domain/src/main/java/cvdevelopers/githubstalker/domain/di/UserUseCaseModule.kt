package cvdevelopers.githubstalker.domain.di

import android.content.Context
import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCase
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCaseImpl
import org.koin.dsl.module

val userUseCaseModule = module {
    fun provideRandomUserUseCase(context: Context, randomUserRepository: RandomUserRepository): RandomUserUseCase {
        return RandomUserUseCaseImpl(context, randomUserRepository)
    }
    single { provideRandomUserUseCase(get(), get()) }
}