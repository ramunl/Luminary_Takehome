package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Info")
data class PageInfo(
        @PrimaryKey
        val page: Int,
        val results: Int,
        val seed: String,
        val version: String
)