package cvdevelopers.githubstalker.data.di

import cvdevelopers.githubstalker.data.network.RandomUserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi {
        return retrofit.create(RandomUserApi::class.java)
    }
    single { provideRandomUserApi(get()) }
}