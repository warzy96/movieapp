package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

class ApiPersonDetails(
    @SerializedName("name")
    val name: String?,

    @SerializedName("birthday")
    val birthday: String?,

    @SerializedName("deathday")
    val deathday: String?,

    @SerializedName("known_for_department")
    val profession: String?,

    @SerializedName("profile_path")
    val profileImageUrl: String?,

    @SerializedName("biography")
    val biography: String?,

    @SerializedName("place_of_birth")
    val placeOfBirth: String?
)
