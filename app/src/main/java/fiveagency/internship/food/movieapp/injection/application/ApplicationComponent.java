package fiveagency.internship.food.movieapp.injection.application;

import javax.inject.Singleton;

import dagger.Component;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.application.module.ApplicationModule;
import fiveagency.internship.food.movieapp.injection.application.module.DataModule;
import fiveagency.internship.food.movieapp.injection.application.module.DomainModule;
import fiveagency.internship.food.movieapp.injection.application.module.UseCaseModule;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class,
                DomainModule.class,
                UseCaseModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        static public ApplicationComponent init(final MovieApplication movieApplication) {
            return DaggerApplicationComponent.builder()
                                             .applicationModule(new ApplicationModule(movieApplication))
                                             .useCaseModule(new UseCaseModule())
                                             .domainModule(new DomainModule())
                                             .dataModule(new DataModule())
                                             .build();
        }

        private Initializer() {

        }
    }

    ActivityComponent.Builder activityComponentBuilder();
}
