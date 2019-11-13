package fiveagency.internship.food.domain.model

class Rating(val source: String, val value: String) {
    companion object {
        @JvmField
        val EMPTY = Rating("", "")

        const val IMDB_SOURCE = "Internet Movie Database"

        const val ROTTEN_TOMATOES_SOURCE = "Rotten Tomatoes"
    }
}