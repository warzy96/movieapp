package fiveagency.internship.food.movieapp.ui.favoriteslist;

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
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListViewModel;
import io.reactivex.disposables.CompositeDisposable;

public final class MovieFavoritesFragment extends BaseFragment<MovieFavoritesContract.Presenter> implements MovieFavoritesContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MovieFavoritesFragment";

    @Inject
    MovieFavoritesAdapter movieFavoritesAdapter;

    @Inject
    CompositeDisposable compositeDisposable;

    @BindView(R.id.movies_list_swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.movies_list_search_text)
    EditText searchEditText;

    @LayoutRes
    private static final int MOVIES_LIST_FRAGMENT = R.layout.fragment_movies_list;

    @BindView(R.id.movies_list_recycler_view)
    RecyclerView moviesListRecyclerView;

    public static MovieFavoritesFragment newInstance() {
        return new MovieFavoritesFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        movieFavoritesAdapter.setOnMovieClickListener(movieId -> presenter.showMovieDetails(movieId));
        movieFavoritesAdapter.setFavoriteOnCheckedListener((movieId, isChecked) -> {
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
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void render(final MoviesListViewModel moviesListViewModel) {
        swipeRefreshLayout.setRefreshing(false);
        movieFavoritesAdapter.setMovies(moviesListViewModel.movieViewModelList);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.getFavoritesUseCase();
    }

    private void initRecyclerView() {
        moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moviesListRecyclerView.setAdapter(movieFavoritesAdapter);
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.start();
        });
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
