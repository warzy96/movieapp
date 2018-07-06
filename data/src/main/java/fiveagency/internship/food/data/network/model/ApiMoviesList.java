package fiveagency.internship.food.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public final class ApiMoviesList {

    @SerializedName("results") public List<ApiMovie> movieApiEntities;

    public ApiMoviesList() {
        movieApiEntities = new LinkedList<>();
    }
}
