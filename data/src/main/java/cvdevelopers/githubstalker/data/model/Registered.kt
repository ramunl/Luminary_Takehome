package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Registered")
data class Registered(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val age: Int,
        val date: String
)