package fiveagency.internship.food.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ApiBeer {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("tagline")
    public String tagLine;

    @SerializedName("first_brewed")
    public String firstBrewed;

    @SerializedName("description")
    public String description;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("abv")
    public double alcoholByVolume;

    @SerializedName("food_pairing")
    public List<String> foodPairing;

    @SerializedName("brewers_tips")
    public String brewersTips;
}
