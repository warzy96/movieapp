package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.base.BaseFragment;

public final class MoviesListFragment extends BaseFragment<MoviesListContract.Presenter> implements MoviesListContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MoviesListFragment";

    @Inject
    MoviesListContract.Presenter presenter;

    @Inject
    MoviesListAdapter moviesListAdapter;

    @BindView(R.id.movies_list_swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.movies_list_recycler_view)
    RecyclerView moviesListRecyclerView;

    @BindView(R.id.movies_list_search_text)
    EditText searchEditText;

    @LayoutRes
    public static final int MOVIES_LIST_FRAGMENT = R.layout.fragment_movies_list;

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        moviesListAdapter.setOnMovieClickListener(movieId -> presenter.showMovieDetails(movieId));
        moviesListAdapter.setFavoriteOnCheckedListener((movieId, isChecked) -> {
            if (isChecked) {
                presenter.insertFavorite(movieId);
            } else {
                presenter.removeFavorite(movieId);
            }
        });
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(MOVIES_LIST_FRAGMENT, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSwipeRefreshLayout();
        searchEditText.setVisibility(View.GONE);
        presenter.start();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void render(final MoviesListViewModel moviesListViewModel) {
        swipeRefreshLayout.setRefreshing(false);
        moviesListAdapter.setMovies(moviesListViewModel.movieViewModelList);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.getMoviesUseCase();
    }

    private void initRecyclerView() {
        moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moviesListRecyclerView.setAdapter(moviesListAdapter);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
