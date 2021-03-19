package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Timezone")
data class Timezone(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val description: String,
        val offset: String
)