package fiveagency.internship.food.movieapp.ui.base;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.router.Router;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View> {

    @Inject
    protected Router router;

    @Inject
    protected CompositeDisposable compositeDisposable;

    public View view;
}
