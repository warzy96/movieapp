package fiveagency.internship.food.data.network.service;

import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("3/movie/popular")
    Single<ApiMoviesList> listMovieEntities(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("3/movie/{id}")
    Single<ApiMovie> movieDetailsEntity(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("3/search/movie")
    Single<ApiMoviesList> searchMovieEntities(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String title);
}
