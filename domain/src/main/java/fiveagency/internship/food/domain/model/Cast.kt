package fiveagency.internship.food.domain.model

class Cast(
    val id: Int?,
    val character: String?,
    val name: String?,
    val profileUrl: String?
) {
    companion object {
        val EMPTY = Cast(0, "", "", "")
    }
}