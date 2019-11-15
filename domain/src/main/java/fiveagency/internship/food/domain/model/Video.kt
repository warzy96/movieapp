package fiveagency.internship.food.domain.model

class Video(
    val key: String,
    val source: String,
    val type: String
) {

    companion object {
        @JvmField
        val EMPTY = Video("", "", "")

        const val TYPE_TRAILER = "Trailer"

        const val SOURCE_YOUTUBE = "YouTube"
    }
}