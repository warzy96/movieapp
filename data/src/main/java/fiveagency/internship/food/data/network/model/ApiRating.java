package fiveagency.internship.food.data.network.model;

import com.google.gson.annotations.SerializedName;

public class ApiRating {

    @SerializedName("Source")
    public String source;

    @SerializedName("Value")
    public String ratingValue;
}
