package fiveagency.internship.food.movieapp.ui.favoriteslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.base.BaseFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListViewModel;
import io.reactivex.disposables.CompositeDisposable;

public final class MovieFavoritesFragment extends BaseFragment<MovieFavoritesContract.Presenter> implements MovieFavoritesContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MovieFavoritesFragment";
    private static final int SPAN_COUNT = 2;

    @Inject
    MovieFavoritesAdapter movieFavoritesAdapter;

    @Inject
    CompositeDisposable compositeDisposable;

    @BindView(R.id.movies_list_swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.background_image)
    AppCompatImageView backgroundImage;

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

        backgroundImage.setScaleType(ImageView.ScaleType.FIT_END);
        backgroundImage.setImageResource(R.drawable.oscar);
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
        moviesListRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
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
