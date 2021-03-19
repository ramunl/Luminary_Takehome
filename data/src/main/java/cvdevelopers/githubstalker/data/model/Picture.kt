package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Picture")
data class Picture(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val large: String,
        val medium: String,
        val thumbnail: String
)