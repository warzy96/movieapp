package fiveagency.internship.food.movieapp.ui.base;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.router.Router;

public abstract class BasePresenter<View> {

    @Inject
    protected Router router;

    public View view;
}
