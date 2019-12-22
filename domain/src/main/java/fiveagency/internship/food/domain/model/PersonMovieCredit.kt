package fiveagency.internship.food.domain.model

data class PersonMovieCredit(
    val backdropPath: String
) {
    companion object {
        val EMPTY = PersonMovieCredit("")
    }
}