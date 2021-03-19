package cvdevelopers.githubstalker.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cvdevelopers.githubstalker.data.model.Results

const val RandomUserTableName = "Results"

@Dao
interface ResultsDao {

    @Query("SELECT * FROM $RandomUserTableName")
    fun findAll(): Results

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(results: Results)
}