package cvdevelopers.githubstalker.domain.entities

const val unknown = "not defined"
const val defaultId = 0

class RandomUserEntity(
        val id: Int = defaultId,
        val name: String? = unknown,
        val avatarUrl: String? = unknown
)
