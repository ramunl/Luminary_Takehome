package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import cvdevelopers.githubstalker.data.di.RandomUserTableName


@Entity(tableName = RandomUserTableName)
data class Results(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        //val info: Info,
        val results: List<RandomUser>?
)