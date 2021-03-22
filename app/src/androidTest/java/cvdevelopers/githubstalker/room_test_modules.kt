package cvdevelopers.githubstalker

import androidx.room.Room
import cvdevelopers.githubstalker.data.repository.ResultsDatabase
import org.koin.dsl.module

val roomTestModule = module(override = true) {
    single {
        Room.inMemoryDatabaseBuilder(get(), ResultsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}