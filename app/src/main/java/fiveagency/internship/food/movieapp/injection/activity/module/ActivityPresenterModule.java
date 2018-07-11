package fiveagency.internship.food.movieapp.injection.activity.module;

import dagger.Module;
import fiveagency.internship.food.movieapp.injection.activity.DaggerActivity;

@Module
public final class ActivityPresenterModule {

    private final DaggerActivity daggerActivity;

    public ActivityPresenterModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }
}
