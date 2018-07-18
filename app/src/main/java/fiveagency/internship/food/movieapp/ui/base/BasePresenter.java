package fiveagency.internship.food.movieapp.ui.base;

import javax.inject.Inject;
import javax.inject.Named;

import fiveagency.internship.food.movieapp.injection.application.module.ThreadingModule;
import fiveagency.internship.food.movieapp.router.Router;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View> {

    @Inject
    protected Router router;

    @Inject
    @Named(ThreadingModule.BACKGROUND_SCHEDULER)
    public Scheduler backgroundScheduler;

    @Inject
    @Named(ThreadingModule.MAIN_SCHEDULER)
    public Scheduler mainThreadScheduler;

    @Inject
    protected CompositeDisposable compositeDisposable;

    public View view;

    //TODO: Call this in BaseFragment
    public void onStop() {
        compositeDisposable.clear();
    }
}
