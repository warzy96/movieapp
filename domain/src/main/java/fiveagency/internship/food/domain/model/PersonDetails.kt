package fiveagency.internship.food.domain.model

class PersonDetails(
    val name: String,
    val birthday: String,
    val deathday: String,
    val profession: String,
    val profileImageUrl: String,
    val biography: String,
    val placeOfBirth: String
) {
    companion object {
        @JvmField
        val EMPTY: PersonDetails = PersonDetails("-", "-", "-", "-", "-", "-", "-")
    }
}
