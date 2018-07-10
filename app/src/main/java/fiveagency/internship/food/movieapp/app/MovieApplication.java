package fiveagency.internship.food.movieapp.app;

import android.app.Application;
import android.content.Context;

import fiveagency.internship.food.movieapp.injection.ObjectGraph;

public final class MovieApplication extends Application {

    private ObjectGraph objectGraph;

    public static MovieApplication from(final Context context) {
        return (MovieApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = new ObjectGraph();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
