package fiveagency.internship.food.movieapp.ui.movieslist

import fiveagency.internship.food.domain.model.WeatherModel

interface MoviesListContract {
    interface Presenter {
        fun start()
        fun showMovieDetails(movieId: Int)
        fun getFlowableMovies()
        fun setView(view: View)
        fun insertFavorite(movieId: Int)
        fun removeFavorite(movieId: Int)
        fun saveMovies(movieViewModelList: List<MovieViewModel>)
        fun getAdditionalMovies(page: Int)
        fun onStop()
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
        fun showSearchScreen()
        fun getFlowableFavoriteRecommendations()
        fun getFlowableWeatherRecommendations()
        fun onRefresh()
    }

    interface View {
        fun render(moviesListViewModel: MoviesListViewModel)
        fun renderWeather(fiveDayForecast: WeatherModel)
    }
}