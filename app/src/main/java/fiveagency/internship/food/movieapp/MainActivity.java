package fiveagency.internship.food.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ObjectGraph;
import fiveagency.internship.food.movieapp.router.Router;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectGraph objectGraph = MovieApplication.from(this).getObjectGraph();
        GetMoviesUseCase getMoviesUseCase = objectGraph.provideGetMoviesUseCase();
        getMoviesUseCase.execute(1, new QueryUseCase.Callback<List<Movie>>() {

            @Override
            public void onSuccess(final List<Movie> movies) {
                Log.d("movies", movies.toString());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        Router router = objectGraph.provideRouter(this);
        router.showMoviesListScreen();
    }
}
