package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Street")
data class Street(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val name: String,
        val number: Int
)