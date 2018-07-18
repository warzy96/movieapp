package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import io.reactivex.disposables.CompositeDisposable;

public final class MoviesListFragment extends DaggerFragment implements MoviesListContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MoviesListFragment";

    @Inject
    MoviesListContract.Presenter presenter;
    @Inject
    MoviesListAdapter moviesListAdapter;
    @Inject
    CompositeDisposable compositeDisposable;

    private SwipeRefreshLayout swipeRefreshLayout;

    private static final int MOVIES_LIST_SWIPE_REFRESH_LAYOUT = R.id.movies_list_swipe_refresh_layout;
    private static final int MOVIES_LIST_FRAGMENT = R.layout.fragment_movies_list;
    private static final int MOVIES_LIST_RECYCLER_VIEW = R.id.movies_list_recycler_view;

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        moviesListAdapter.setOnMovieClickListener(movieId -> presenter.showMovieDetails(movieId));
        moviesListAdapter.setFavoriteOnCheckedListener((movieId, isChecked) -> {
            if (!isChecked) {
                presenter.insertFavorite(movieId);
            } else {
                presenter.removeFavorite(movieId);
            }
        });
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(MOVIES_LIST_FRAGMENT, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
        initSwipeRefreshLayout();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = getView().findViewById(MOVIES_LIST_SWIPE_REFRESH_LAYOUT);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.start();
        });
    }

    @Override
    public void render(final MoviesListViewModel moviesListViewModel) {
        swipeRefreshLayout.setRefreshing(false);
        moviesListAdapter.setMovies(moviesListViewModel.movieViewModelList);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.start();
    }

    private void initRecyclerView(final View rootView) {
        final RecyclerView recyclerView = rootView.findViewById(MOVIES_LIST_RECYCLER_VIEW);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(moviesListAdapter);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
