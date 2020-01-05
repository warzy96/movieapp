package fiveagency.internship.food.movieapp.ui.movieslist

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fiveagency.internship.food.domain.interactor.*
import fiveagency.internship.food.domain.model.*
import fiveagency.internship.food.movieapp.injection.activity.ForActivity
import fiveagency.internship.food.movieapp.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class MoviesListPresenter : BasePresenter<MoviesListContract.View>(), MoviesListContract.Presenter {
    private var moviesCache: MutableList<MovieViewModel> = ArrayList()

    @field:ForActivity
    @Inject
    lateinit var activityContext: Context

    @Inject
    @field:ForActivity
    lateinit var activity: Activity

    @Inject
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Inject
    lateinit var insertFavoriteUseCase: InsertFavoriteUseCase

    @Inject
    lateinit var movieViewModelMapper: MovieViewModelMapper

    @Inject
    lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase
    @Inject
    lateinit var getFlowableMoviesUseCase: GetFlowableMoviesUseCase

    @Inject
    lateinit var saveMoviesUseCase: SaveMoviesUseCase

    @Inject
    lateinit var fetchFiveDayForecastUseCase: FetchFiveDayForecastUseCase

    @Inject
    lateinit var searchCitiesByCoordinatesUseCase: SearchCitiesByCoordinatesUseCase

    @Inject
    lateinit var getFavoriteMoviesRecommendationsUseCase: GetFavoriteMoviesRecommendationsUseCase

    @Inject
    lateinit var getFlowableWeatherRecommendationsUseCase: GetFlowableWeatherRecommendationsUseCase

    private var fetchFunction: () -> Unit = ::getFlowableMovies
    private lateinit var currentLocation: Location
    private lateinit var locationManager: LocationManager
    private val locationListener: LocationListener = object : LocationListener {
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onLocationChanged(location: android.location.Location) {
            currentLocation = Location(location.latitude, location.longitude)
            loadWeather()
        }
    }

    override fun start() {
        locationManager = activityContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fetchFunction()
        checkLocationPermission()
    }

    override fun getFlowableMovies() {
        fetchFunction = ::getFlowableMovies
        compositeDisposable.add(
            getFlowableMoviesUseCase.execute(DEFAULT_PAGE)
                .map { movieList: List<Movie> ->
                    movieViewModelMapper.mapMoviesListViewModel(movieList)
                }
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(
                    { moviesListViewModel: MoviesListViewModel ->
                        moviesCache = ArrayList()
                        moviesCache.addAll(moviesListViewModel.movieViewModelList)
                        view.render(moviesListViewModel)
                    }
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    override fun getAdditionalMovies(page: Int) {
        if (fetchFunction == ::getFlowableMovies) {
            compositeDisposable.add(
                getFlowableMoviesUseCase.execute(page)
                    .map { movieList: List<Movie> ->
                        movieViewModelMapper.mapMoviesListViewModel(movieList)
                    }
                    .subscribeOn(backgroundScheduler)
                    .observeOn(mainThreadScheduler)
                    .subscribe(
                        { moviesListViewModel: MoviesListViewModel ->
                            moviesCache.addAll(moviesListViewModel.movieViewModelList)
                            view.render(MoviesListViewModel(moviesCache))
                        }
                    ) { throwable: Throwable -> loggerImpl.log(throwable) }
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkLocationPermission()
        }
    }

    private fun loadWeather() {
        compositeDisposable.add(
            searchCitiesByCoordinatesUseCase.execute(currentLocation)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(
                    { citySearchResults: CitySearchResults ->
                        fetchFiveDayForecast(
                            citySearchResults.cities[0].woeid
                        )
                    }
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(activityContext, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location != null) {
                currentLocation = Location(location.latitude, location.longitude)
                loadWeather()
            }
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE.toFloat(), locationListener
            )
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            RC_LOCATION
        )
    }

    private fun fetchFiveDayForecast(cityId: Int) {
        compositeDisposable.add(
            fetchFiveDayForecastUseCase.execute(cityId)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({ fiveDayForecast: WeatherModel ->
                    saveWeather(fiveDayForecast.weatherDetailsList[0].weatherState)
                    view.renderWeather(fiveDayForecast)
                }) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    private fun saveWeather(weatherName: String) {
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()
        val userId = firebaseAuth.uid
        if (userId != null) {
            firebaseFirestore.collection("weather")
                .document(userId)
                .set(CurrentWeatherModel(weatherName))
        }
    }

    override fun showMovieDetails(movieId: Int) {
        router.showMovieDetailsScreen(movieId)
    }

    override fun setView(view: MoviesListContract.View) {
        this.view = view
    }

    override fun insertFavorite(favoriteMovieModel: FavoriteMovieModel) {
        compositeDisposable.add(
            insertFavoriteUseCase.execute(FavoriteMovie(favoriteMovieModel.movieId, favoriteMovieModel.genres))
                .subscribeOn(backgroundScheduler)
                .subscribe(
                    {}
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    override fun removeFavorite(favoriteMovieModel: FavoriteMovieModel) {
        compositeDisposable.add(
            removeFavoriteUseCase.execute(FavoriteMovie(favoriteMovieModel.movieId, favoriteMovieModel.genres))
                .subscribeOn(backgroundScheduler)
                .subscribe(
                    {}
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    override fun saveMovies(movieViewModelList: List<MovieViewModel>) {
        compositeDisposable.add(
            saveMoviesUseCase.execute(movieViewModelMapper.mapMovies(movieViewModelList))
                .subscribeOn(backgroundScheduler)
                .subscribe(
                    {}
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    override fun showSearchScreen() {
        router.showMoviesSearchScreen()
    }

    override fun onRefresh() {
        fetchFunction()
    }

    override fun getFlowableFavoriteRecommendations() {
        fetchFunction = ::getFlowableFavoriteRecommendations
        compositeDisposable.add(
            getFavoriteMoviesRecommendationsUseCase.execute()
                .map { movieList: List<Movie> ->
                    movieViewModelMapper.mapMoviesListViewModel(movieList)
                }
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(
                    { moviesListViewModel: MoviesListViewModel ->
                        moviesCache = ArrayList()
                        moviesCache.addAll(moviesListViewModel.movieViewModelList)
                        view.render(moviesListViewModel)
                    }
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    override fun getFlowableWeatherRecommendations() {
        fetchFunction = ::getFlowableWeatherRecommendations
        compositeDisposable.add(
            getFlowableWeatherRecommendationsUseCase.execute()
                .map { movieList: List<Movie> ->
                    movieViewModelMapper.mapMoviesListViewModel(movieList)
                }
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(
                    { moviesListViewModel: MoviesListViewModel ->
                        moviesCache = ArrayList()
                        moviesCache.addAll(moviesListViewModel.movieViewModelList)
                        view.render(moviesListViewModel)
                    }
                ) { throwable: Throwable -> loggerImpl.log(throwable) }
        )
    }

    companion object {
        const val RC_LOCATION = 123
        private const val DEFAULT_PAGE = 1
        private const val LOCATION_REFRESH_DISTANCE: Long = 1000
        private const val LOCATION_REFRESH_TIME: Long = 1800000
    }
}