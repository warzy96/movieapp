package fiveagency.internship.food.movieapp.ui.base;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;

public abstract class BaseFragment<Presenter> extends DaggerFragment {

    @Inject
    public Presenter presenter;

    @Override
    public void onStop() {
        super.onStop();
    }
}
