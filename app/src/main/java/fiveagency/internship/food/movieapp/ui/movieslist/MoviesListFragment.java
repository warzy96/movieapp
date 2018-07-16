package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.MainActivity;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ObjectGraph;

public final class MoviesListFragment extends Fragment implements MoviesListContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MoviesListFragment";
    private MoviesListContract.Presenter presenter;
    private MoviesListAdapter moviesListAdapter;
    private ObjectGraph objectGraph;
    @BindView(R.id.movies_list_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @LayoutRes
    private static final int MOVIES_LIST_FRAGMENT = R.layout.fragment_movies_list;
    @BindView(R.id.movies_list_recycler_view)
    RecyclerView moviesListRecyclerView;

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objectGraph = MovieApplication.from(getContext()).getObjectGraph();
        presenter = objectGraph.provideMoviesListPresenter(this, objectGraph.provideRouter((MainActivity) getActivity()));
        moviesListAdapter = objectGraph.provideMoviesListAdapter(getContext());
        moviesListAdapter.setOnMovieClickListener(movieId -> presenter.showMovieDetails(movieId));
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
}
