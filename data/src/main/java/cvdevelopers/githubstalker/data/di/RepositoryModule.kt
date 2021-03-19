package cvdevelopers.githubstalker.data.di

import android.content.Context
import cvdevelopers.githubstalker.data.network.RandomUserApi
import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.data.repository.RandomUserRepositoryImpl
import cvdevelopers.githubstalker.data.repository.ResultsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRandomUserRepository(api: RandomUserApi, context: Context, database: ResultsDatabase): RandomUserRepository {
        return RandomUserRepositoryImpl(api, context, database)
    }
    single { provideRandomUserRepository(get(), androidContext(), get()) }

}