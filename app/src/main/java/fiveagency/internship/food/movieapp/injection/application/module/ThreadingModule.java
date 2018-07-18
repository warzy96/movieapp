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

    @Provides
    @Singleton
    @Named("IOThread")
    Scheduler provideIOThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("MainThread")
    Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
