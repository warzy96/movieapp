package fiveagency.internship.food.data

data class Location(val latitude: Double?, val longitude: Double?) {

    override fun toString(): String {
        return "$latitude, $longitude"
    }
}