package fiveagency.internship.food.movieapp.ui.movieslist;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import fiveagency.internship.food.domain.interactor.FetchFiveDayForecastUseCase;
import fiveagency.internship.food.domain.interactor.GetFlowableMoviesUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.SaveMoviesUseCase;
import fiveagency.internship.food.domain.interactor.SearchCitiesByCoordinatesUseCase;
import fiveagency.internship.food.movieapp.injection.activity.ForActivity;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

import static android.content.Context.LOCATION_SERVICE;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    public static final int RC_LOCATION = 123;
    private static final int DEFAULT_PAGE = 1;
    private static final long LOCATION_REFRESH_DISTANCE = 1000;
    private static final long LOCATION_REFRESH_TIME = 1800000;

    private List<MovieViewModel> moviesCache = new ArrayList<>();

    @Inject
    @ForActivity
    Context activityContext;

    @Inject
    @ForActivity
    Activity activity;

    @Inject
    GetMoviesUseCase getMoviesUseCase;

    @Inject
    InsertFavoriteUseCase insertFavoriteUseCase;

    @Inject
    MovieViewModelMapper movieViewModelMapper;

    @Inject
    RemoveFavoriteUseCase removeFavoriteUseCase;

    @Inject
    GetFlowableMoviesUseCase getFlowableMoviesUseCase;

    @Inject
    SaveMoviesUseCase saveMoviesUseCase;

    @Inject
    FetchFiveDayForecastUseCase fetchFiveDayForecastUseCase;

    @Inject
    SearchCitiesByCoordinatesUseCase searchCitiesByCoordinatesUseCase;

    private fiveagency.internship.food.domain.model.Location currentLocation;

    private LocationManager locationManager;

    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onProviderEnabled(final String provider) {

        }

        @Override
        public void onProviderDisabled(final String provider) {

        }

        @Override
        public void onStatusChanged(final String provider, final int status, final Bundle extras) {
        }

        @Override
        public void onLocationChanged(final Location location) {
            currentLocation = new fiveagency.internship.food.domain.model.Location(location.getLatitude(), location.getLongitude());
            loadWeather();
        }
    };

    @Override
    public void start() {
        locationManager = (LocationManager) activityContext.getSystemService(LOCATION_SERVICE);
        getFlowableMoviesUseCase();
        checkLocationPermission();
    }

    @Override
    public void getFlowableMoviesUseCase() {
        compositeDisposable.add(getFlowableMoviesUseCase.execute(DEFAULT_PAGE)
                                                        .map(movieViewModelMapper::mapMoviesListViewModel)
                                                        .subscribeOn(backgroundScheduler)
                                                        .observeOn(mainThreadScheduler)
                                                        .subscribe(moviesListViewModel -> {
                                                                       moviesCache = new ArrayList<>();
                                                                       moviesCache.addAll(moviesListViewModel.movieViewModelList);
                                                                       view.render(moviesListViewModel);
                                                                   },
                                                                   throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void getAdditionalMovies(final int page) {
        compositeDisposable.add(getFlowableMoviesUseCase.execute(page)
                                                        .map(movieViewModelMapper::mapMoviesListViewModel)
                                                        .subscribeOn(backgroundScheduler)
                                                        .observeOn(mainThreadScheduler)
                                                        .subscribe(moviesListViewModel -> {
                                                                       moviesCache.addAll(moviesListViewModel.movieViewModelList);
                                                                       view.render(new MoviesListViewModel(moviesCache));
                                                                   },
                                                                   throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkLocationPermission();
        }
    }

    private void loadWeather() {
        if (currentLocation != null) {
            compositeDisposable.add(searchCitiesByCoordinatesUseCase.execute(currentLocation)
                                                                    .subscribeOn(backgroundScheduler)
                                                                    .observeOn(mainThreadScheduler)
                                                                    .subscribe(citySearchResults -> fetchFiveDayForecast(citySearchResults.getCities().get(0).getWoeid()),
                                                                               throwable -> loggerImpl.log(throwable)));
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(activityContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                currentLocation = new fiveagency.internship.food.domain.model.Location(location.getLatitude(), location.getLongitude());
                loadWeather();
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME,
                                                   LOCATION_REFRESH_DISTANCE, locationListener);
        } else {
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, RC_LOCATION);
    }

    private void fetchFiveDayForecast(int cityId) {
        compositeDisposable.add(fetchFiveDayForecastUseCase.execute(cityId)
                                                           .subscribeOn(backgroundScheduler)
                                                           .observeOn(mainThreadScheduler)
                                                           .subscribe(fiveDayForecast -> {
                                                               view.renderWeather(fiveDayForecast);
                                                           }, throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void showMovieDetails(final int movieId) {
        router.showMovieDetailsScreen(movieId);
    }

    @Override
    public void setView(final MoviesListContract.View view) {
        this.view = view;
    }

    @Override
    public void insertFavorite(final int movieId) {
        compositeDisposable.add(insertFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void removeFavorite(final int movieId) {
        compositeDisposable.add(removeFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void saveMovies(final List<MovieViewModel> movieViewModelList) {
        compositeDisposable.add(saveMoviesUseCase.execute(movieViewModelMapper.mapMovies(movieViewModelList))
                                                 .subscribeOn(backgroundScheduler)
                                                 .subscribe(() -> {},
                                                            throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void showSearchScreen() {
        router.showMoviesSearchScreen();
    }
}
