package fiveagency.internship.food.data.network.service;

import java.util.List;

import fiveagency.internship.food.data.network.model.ApiBeer;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface BeerService {

    @GET("beers/random")
    Single<List<ApiBeer>> fetchRandomBeer();
}
