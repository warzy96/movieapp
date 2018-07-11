package fiveagency.internship.food.movieapp.app;

import android.app.Application;
import android.content.Context;

import fiveagency.internship.food.movieapp.injection.ComponentFactory;
import fiveagency.internship.food.movieapp.injection.application.ApplicationComponent;

public final class MovieApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static MovieApplication from(final Context context) {
        return (MovieApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        injectMe();
    }

    private void injectMe() {
        applicationComponent.inject(this);
    }

    private void initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
