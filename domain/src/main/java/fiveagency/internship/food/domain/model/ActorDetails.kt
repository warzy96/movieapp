package fiveagency.internship.food.domain.model

data class ActorDetails(
    val actorMovieCredits: PersonMovieCredits,
    val name: String,
    val birthday: String,
    val deathday: String,
    val profession: String,
    val profileImageUrl: String,
    val biography: String,
    val placeOfBirth: String
)
