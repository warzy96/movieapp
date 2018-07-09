package fiveagency.internship.food.data.network.model;

import com.google.gson.annotations.SerializedName;

public final class ApiMovie {

    @SerializedName("title")
    public String title;

    @SerializedName("id")
    public int id;

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String releaseDate;
}
