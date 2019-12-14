package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiCity(

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("location_type")
    @Expose
    val locationType: String,

    @SerializedName("woeid")
    @Expose
    val woeid: Int

)