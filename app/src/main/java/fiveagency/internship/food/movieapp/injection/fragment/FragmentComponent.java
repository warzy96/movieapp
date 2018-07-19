package fiveagency.internship.food.movieapp.injection.fragment;

import dagger.Subcomponent;
import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.fragment.module.FragmentModule;
import fiveagency.internship.food.movieapp.injection.fragment.module.FragmentPresenterModule;

@FragmentScope
@Subcomponent(
        modules = {
                FragmentModule.class,
                FragmentPresenterModule.class
        }
)
public interface FragmentComponent extends FragmentComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        Builder fragmentModule(final FragmentModule fragmentModule);

        FragmentComponent build();

        Builder fragmentPresenterModule(final FragmentPresenterModule fragmentPresenterModule);
    }

    final class Initializer {

        static public FragmentComponent init(final DaggerFragment fragment, final ActivityComponent activityComponent) {
            return activityComponent.fragmentComponentBuilder()
                                    .fragmentModule(new FragmentModule(fragment))
                                    .fragmentPresenterModule(new FragmentPresenterModule(fragment))
                                    .build();
        }

        private Initializer() {

        }
    }
}
