package fiveagency.internship.food.movieapp.injection.application.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public final class ThreadingModule {

    public static final String BACKGROUND_SCHEDULER = "background_scheduler";
    public static final String MAIN_SCHEDULER = "main_scheduler";

    @Provides
    @Singleton
    @Named(BACKGROUND_SCHEDULER)
    Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(MAIN_SCHEDULER)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
