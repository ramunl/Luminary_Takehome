package cvdevelopers.githubstalker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Result")
data class RandomUser(
        @PrimaryKey(autoGenerate = true) val primaryId: Int,
        //val cell: String,
        //val dob: Dob,
        //val email: String,
        //val gender: String,
        //val id: Id,
        //val location: Location,
        //val login: Login,
        val name: Name,
        //val nat: String,
        //val phone: String,
        val picture: Picture,
        //val registered: Registered
)