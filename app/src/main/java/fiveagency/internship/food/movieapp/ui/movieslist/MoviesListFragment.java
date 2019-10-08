package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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

    private static final int DEFAULT_PAGE = 1;

    private int page = DEFAULT_PAGE;

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
        presenter.start();
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

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void render(final MoviesListViewModel moviesListViewModel) {
        swipeRefreshLayout.setRefreshing(false);
        moviesListAdapter.setMovies(moviesListViewModel.movieViewModelList);
        presenter.saveMovies(moviesListViewModel.movieViewModelList);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        page = DEFAULT_PAGE;
        presenter.getFlowableMoviesUseCase();
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        moviesListRecyclerView.setLayoutManager(layoutManager);
        moviesListRecyclerView.setAdapter(moviesListAdapter);
        moviesListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int totalItemCount = layoutManager.getItemCount();
                final int lastItem = layoutManager.findLastVisibleItemPosition();
                if (lastItem >= totalItemCount - 1) {
                    presenter.getAdditionalMovies(++page);
                }
            }
        });
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
