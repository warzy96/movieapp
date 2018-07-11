package fiveagency.internship.food.movieapp.injection.activity;

import dagger.Subcomponent;
import fiveagency.internship.food.movieapp.injection.activity.module.ActivityModule;
import fiveagency.internship.food.movieapp.injection.activity.module.ActivityPresenterModule;
import fiveagency.internship.food.movieapp.injection.application.ApplicationComponent;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;

@ActivityScope
@Subcomponent(
        modules = {
                ActivityModule.class,
                ActivityPresenterModule.class,
        }
)
public interface ActivityComponent extends ActivityComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        Builder activityModule(ActivityModule activityModule);

        ActivityComponent build();

        Builder activityPresenterModule(ActivityPresenterModule activityPresenterModule);

    }

    final class Initializer {

        static public ActivityComponent init(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
            return applicationComponent.activityComponentBuilder()
                                       .activityModule(new ActivityModule(daggerActivity))
                                       .activityPresenterModule(new ActivityPresenterModule(daggerActivity))
                                       .build();
        }

        private Initializer() {

        }
    }

    FragmentComponent.Builder fragmentComponentBuilder();
}
