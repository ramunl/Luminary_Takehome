package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import cvdevelopers.githubstalker.data.di.ResultsPageTableName


@Entity(tableName = ResultsPageTableName)
data class ResultsPage(
        //we use Info page id as a primary id
        @PrimaryKey var page: Int,
        val info: PageInfo,
        val results: List<RandomUser>?
)