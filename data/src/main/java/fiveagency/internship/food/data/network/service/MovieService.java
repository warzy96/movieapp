package fiveagency.internship.food.data.network.service;

import fiveagency.internship.food.data.network.model.ApiCredits;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.data.network.model.ApiPersonDetails;
import fiveagency.internship.food.data.network.model.ApiPersonMovieCredits;
import fiveagency.internship.food.data.network.model.ApiVideos;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("3/movie/popular")
    Single<ApiMoviesList> listMovieEntities(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("3/movie/popular")
    Single<ApiMoviesList> listFlowableMovieEntities(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("3/movie/{id}")
    Single<ApiMovie> movieDetailsEntity(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("3/movie/{id}/credits")
    Single<ApiCredits> movieCreditsEntity(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/search/movie")
    Single<ApiMoviesList> searchMovieEntities(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String title);

    @GET("3/movie/{id}/videos")
    Single<ApiVideos> movieVideosEntity(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("3/person/{id}/movie_credits")
    Single<ApiPersonMovieCredits> personMovieCreditsEntity(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("3/person/{id}")
    Single<ApiPersonDetails> personDetails(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language);
}
