package cvdevelopers.githubstalker.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cvdevelopers.githubstalker.data.model.Picture
import cvdevelopers.githubstalker.data.model.RandomUser
import cvdevelopers.githubstalker.data.model.Results
import cvdevelopers.githubstalker.data.repository.converters.NameConverter
import cvdevelopers.githubstalker.data.repository.converters.PictureConverter
import cvdevelopers.githubstalker.data.repository.converters.RandomUserConverter

@Database(
        entities = [
            Results::class,
            RandomUser::class,
            Picture::class,
            //Coordinates::class, Dob::class, Id::class, Info::class, Location::class, Login::class, Name::class,
            //Registered::class, Result::class, Street::class, Timezone::class
        ],
        version = 1, exportSchema = false
)

@TypeConverters(
        RandomUserConverter::class,
        PictureConverter::class,
        NameConverter::class
)

abstract class ResultsDatabase : RoomDatabase() {
    abstract val resultsDao: ResultsDao
}