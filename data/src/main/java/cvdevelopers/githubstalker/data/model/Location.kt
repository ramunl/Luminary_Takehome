package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Location")
data class Location(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        val city: String,
        val coordinates: Coordinates,
        val country: String,
        val postcode: Any,
        val state: String,
        val street: Street,
        val timezone: Timezone
)