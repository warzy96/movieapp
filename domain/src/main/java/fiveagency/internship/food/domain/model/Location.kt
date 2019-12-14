package fiveagency.internship.food.domain.model

val EMPTY = Location(0.0, 0.0)

data class Location(val latitude: Double, val longitude: Double) {

    override fun toString(): String {
        return "$latitude, $longitude"
    }
}