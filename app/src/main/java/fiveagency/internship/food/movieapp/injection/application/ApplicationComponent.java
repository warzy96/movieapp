package fiveagency.internship.food.movieapp.injection.application;

import javax.inject.Singleton;

import dagger.Component;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.application.module.ApplicationModule;
import fiveagency.internship.food.movieapp.injection.application.module.DataModule;
import fiveagency.internship.food.movieapp.injection.application.module.ThreadingModule;
import fiveagency.internship.food.movieapp.injection.application.module.UseCaseModule;
import fiveagency.internship.food.movieapp.injection.application.module.UtilsModule;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class,
                UseCaseModule.class,
                UtilsModule.class,
                UseCaseModule.class,
                ThreadingModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        static public ApplicationComponent init(final MovieApplication movieApplication) {
            return DaggerApplicationComponent.builder()
                                             .applicationModule(new ApplicationModule(movieApplication))
                                             .useCaseModule(new UseCaseModule())
                                             .dataModule(new DataModule())
                                             .threadingModule(new ThreadingModule())
                                             .utilsModule(new UtilsModule())
                                             .build();
        }

        private Initializer() {

        }
    }

    ActivityComponent.Builder activityComponentBuilder();
}
