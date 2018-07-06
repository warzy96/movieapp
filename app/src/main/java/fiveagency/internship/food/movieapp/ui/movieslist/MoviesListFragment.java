package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fiveagency.internship.food.movieapp.R;

public final class MoviesListFragment extends Fragment implements MoviesListContract.View {

    private MoviesListContract.Presenter presenter;

    public MoviesListFragment() {
        presenter = new MoviesListPresenter(this);
    }

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        return rootView;
    }
}
