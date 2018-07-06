package fiveagency.internship.food.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.client.ResponseListener;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.domain.model.Movie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*movieClient.getMovies(new ResponseListener<List<Movie>>() {

            @Override
            public void onResult(final List<Movie> movies) {
                Log.d("movie", movies.toString());
            }
        }, 1);*/
    }
}
