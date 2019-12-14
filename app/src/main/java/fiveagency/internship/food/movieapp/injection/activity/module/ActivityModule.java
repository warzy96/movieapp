package fiveagency.internship.food.movieapp.injection.activity.module;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentManager;
import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.injection.activity.ActivityScope;
import fiveagency.internship.food.movieapp.injection.activity.DaggerActivity;
import fiveagency.internship.food.movieapp.injection.activity.ForActivity;
import fiveagency.internship.food.movieapp.router.Router;

@Module
public final class ActivityModule {

    private final DaggerActivity daggerActivity;

    public ActivityModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    Context provideActivityContext() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    Activity provideActivity() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    Router provideRouter(final FragmentManager fragmentManager) {
        return new Router(daggerActivity, fragmentManager);
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return daggerActivity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater() {
        return daggerActivity.getLayoutInflater();
    }
}
