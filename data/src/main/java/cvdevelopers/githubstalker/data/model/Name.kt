package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Name")
data class Name(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val first: String,
        val last: String,
        val title: String
)