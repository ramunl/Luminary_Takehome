package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Dob")
data class Dob(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val age: Int,
        val date: String
)