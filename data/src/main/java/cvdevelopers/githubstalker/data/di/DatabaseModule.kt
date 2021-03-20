package cvdevelopers.githubstalker.data.di

import android.app.Application
import androidx.room.Room
import cvdevelopers.githubstalker.data.repository.ResultsDao
import cvdevelopers.githubstalker.data.repository.ResultsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


const val RandomUserTableName = "random_user_table"

val databaseModule = module {

    fun provideDatabase(application: Application): ResultsDatabase {
        return Room.databaseBuilder(application, ResultsDatabase::class.java, RandomUserTableName)
                .fallbackToDestructiveMigration()
                .build()
    }

    fun provideRandomUsersDao(database: ResultsDatabase): ResultsDao {
        return database.resultsDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideRandomUsersDao(get()) }
}
