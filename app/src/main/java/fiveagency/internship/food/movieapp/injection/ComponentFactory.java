package fiveagency.internship.food.movieapp.injection;

import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.activity.DaggerActivity;
import fiveagency.internship.food.movieapp.injection.application.ApplicationComponent;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;

public final class ComponentFactory {

    private ComponentFactory() {

    }

    public static ApplicationComponent createApplicationComponent(final MovieApplication movieApplication) {
        return ApplicationComponent.Initializer.init(movieApplication);
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
        return ActivityComponent.Initializer.init(daggerActivity, applicationComponent);
    }

    public static FragmentComponent createFragmentComponent(final DaggerFragment daggerFragment, final ActivityComponent activityComponent) {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent);
    }
}
