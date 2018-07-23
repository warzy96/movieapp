package fiveagency.internship.food.movieapp.ui.searchlist;

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

public final class MoviesSearchFragment extends BaseFragment<MoviesSearchContract.Presenter> implements MoviesSearchContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MoviesSearchFragment";

    @BindView(R.id.movies_list_recycler_view)
    RecyclerView moviesListRecyclerView;

    @BindView(R.id.movies_list_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.movies_list_search_text)
    EditText searchEditText;

    @LayoutRes
    public static final int MOVIES_LIST_FRAGMENT = R.layout.fragment_movies_list;

    @Inject
    MoviesSearchAdapter moviesListAdapter;

    @Inject
    MoviesSearchContract.Presenter presenter;

    public static MoviesSearchFragment newInstance() {
        return new MoviesSearchFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        moviesListAdapter.setOnMovieClickListener(movieId -> presenter.showMovieDetails(movieId));
        moviesListAdapter.setFavoriteOnCheckedListener(((movie, isChecked) -> {
            if (isChecked) {
                presenter.insertFavorite(movie);
            } else {
                presenter.removeFavorite(movie.id);
            }
        }));
    }

    @Nullable
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
        presenter.start(searchEditText);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        final String query = searchEditText.getText().toString();
        if (query.trim().isEmpty()) {
            swipeRefreshLayout.setRefreshing(false);
        } else {
            presenter.refreshSearch(searchEditText.getText().toString());
        }
    }

    private void initRecyclerView() {
        moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moviesListRecyclerView.setAdapter(moviesListAdapter);
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void render(final MoviesListViewModel moviesListViewModel) {
        swipeRefreshLayout.setRefreshing(false);
        moviesListAdapter.setMovies(moviesListViewModel.movieViewModelList);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
