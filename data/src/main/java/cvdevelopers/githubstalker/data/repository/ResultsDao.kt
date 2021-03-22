package cvdevelopers.githubstalker.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cvdevelopers.githubstalker.data.di.ResultsPageTableName
import cvdevelopers.githubstalker.data.model.ResultsPage


@Dao
interface ResultsDao {

    @Query("SELECT * FROM $ResultsPageTableName")
    fun findAll(): List<ResultsPage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(resultsPage: ResultsPage)
}