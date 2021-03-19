package cvdevelopers.githubstalker.domain.mapper

import cvdevelopers.githubstalker.data.model.RandomUser
import cvdevelopers.githubstalker.domain.entities.RandomUserEntity

fun mapToEntity(randomUser: RandomUser): RandomUserEntity = RandomUserEntity(
        randomUser.primaryId,
        "${randomUser.name.first} ${randomUser.name.last}",
        randomUser.picture.thumbnail
)

fun mapToEntity(type: List<RandomUser>): List<RandomUserEntity> = type.map { mapToEntity(it) }
