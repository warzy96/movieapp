package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.data.network.configuration.Urls;
import fiveagency.internship.food.domain.model.WeatherModel;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.base.BaseFragment;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

public final class MoviesListFragment extends BaseFragment<MoviesListContract.Presenter> implements MoviesListContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MoviesListFragment";
    private static int SPAN_COUNT = 2;

    private static final String IMAGE_FORMAT = ".png";

    @Inject
    MoviesListContract.Presenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Inject
    MoviesListAdapter moviesListAdapter;

    @BindView(R.id.movies_list_swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;

    @BindView(R.id.movies_list_recycler_view)
    RecyclerView moviesListRecyclerView;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        setStatusBarColor(R.color.tabBackgroundColor);
        presenter.start();
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
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void renderWeather(final WeatherModel fiveDayForecast) {
        imageLoader.renderImage(parseImageUrl(fiveDayForecast.getWeatherDetailsList().get(0).getWeatherStateAbbr()), weatherIcon, 0f);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        page = DEFAULT_PAGE;
        presenter.getFlowableMoviesUseCase();
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
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

    private String parseImageUrl(String weatherStateAbbr) {
        return Urls.ICONS_BASE_URL + weatherStateAbbr + IMAGE_FORMAT;
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
