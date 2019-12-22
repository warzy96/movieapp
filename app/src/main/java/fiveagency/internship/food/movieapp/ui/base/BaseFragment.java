package fiveagency.internship.food.movieapp.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;

public abstract class BaseFragment<Presenter> extends DaggerFragment {

    @Inject
    public Presenter presenter;

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarColor(R.color.tabBackgroundColor);
    }

    protected void setStatusBarColor(@ColorRes int color) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.getWindow().setStatusBarColor(getResources().getColor(color));
        }
    }
}
