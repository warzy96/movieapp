package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

class ApiVideos {

    @SerializedName("results")
    var videos: List<ApiVideo?>
        private set

    init {
        videos = listOf()
    }

}