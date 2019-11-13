package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

class ApiRatings {

    @SerializedName("Ratings")
    var ratings: List<ApiRating>
        private set

    init {
        ratings = listOf()
    }

    constructor(ratings: List<ApiRating>) {
        this.ratings = ratings
    }
}