package fiveagency.internship.food.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ObjectGraph;
import fiveagency.internship.food.movieapp.router.Router;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ObjectGraph objectGraph = MovieApplication.from(this).getObjectGraph();
        final Router router = objectGraph.provideRouter(this);
        router.showMoviesListScreen();
    }
}
