package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Id")
data class Id(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val name: String,
        val value: String
)