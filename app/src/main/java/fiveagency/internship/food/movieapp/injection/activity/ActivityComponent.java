package fiveagency.internship.food.movieapp.injection.activity;

import dagger.Subcomponent;
import fiveagency.internship.food.movieapp.injection.activity.module.ActivityModule;
import fiveagency.internship.food.movieapp.injection.application.ApplicationComponent;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;

@ActivityScope
@Subcomponent(
        modules = {
                ActivityModule.class,
        }
)
public interface ActivityComponent extends ActivityComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        Builder activityModule(ActivityModule activityModule);

        ActivityComponent build();
    }

    final class Initializer {

        static public ActivityComponent init(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
            return applicationComponent.activityComponentBuilder()
                                       .activityModule(new ActivityModule(daggerActivity))
                                       .build();
        }

        private Initializer() {

        }
    }

    FragmentComponent.Builder fragmentComponentBuilder();
}
