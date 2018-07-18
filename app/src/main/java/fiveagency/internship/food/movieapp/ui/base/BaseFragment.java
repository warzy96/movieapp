package fiveagency.internship.food.movieapp.ui.base;

import android.support.v4.widget.SwipeRefreshLayout;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;

public abstract class BaseFragment<Presenter> extends DaggerFragment {

    @Inject
    public Presenter presenter;

    public SwipeRefreshLayout swipeRefreshLayout;
}
