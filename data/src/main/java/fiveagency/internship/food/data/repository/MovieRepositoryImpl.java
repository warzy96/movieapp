package fiveagency.internship.food.data.repository;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import fiveagency.internship.food.data.database.crudder.MovieCrudder;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.domain.model.ActorDetails;
import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.model.PersonMovieCredits;
import fiveagency.internship.food.domain.model.Rating;
import fiveagency.internship.food.domain.model.Video;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public final class MovieRepositoryImpl implements MovieRepository {

    private final MovieClient movieClient;
    private final MovieCrudder movieCrudder;

    public MovieRepositoryImpl(final MovieClient movieClient, final MovieCrudder movieCrudder) {
        this.movieClient = movieClient;
        this.movieCrudder = movieCrudder;
    }

    @Override
    public Single<Movie> fetchMovieDetails(final int id) {
        return Single.zip(movieClient.getMovieDetails(id), movieCrudder.movieExists(id), movieCrudder.getAllFavoriteMoviesIds(), movieClient.getMovieVideos(id),
                          (movieDetails, dbMovie, favorites, videos) -> {
                              List<Video> filteredVideos = filterVideos(videos);
                              Movie result = movieDetails.withVideos(filteredVideos);
                              for (final int favorite : favorites) {
                                  if (favorite == movieDetails.id) {
                                      result = result.withIsFavorite(true);
                                  }
                              }
                              if (dbMovie.equals(Movie.EMPTY)) {
                                  return result;
                              } else {
                                  return result.withPersonalNote(dbMovie.personalNote);
                              }
                          });
    }

    private List<Video> filterVideos(final List<Video> videos) {
        return Stream.of(videos).filter(video -> video.getSource().equals(Video.SOURCE_YOUTUBE)).toList();
    }

    @Override
    public Single<List<Cast>> fetchMovieCast(final int movieId) {
        return movieClient.getMovieCast(movieId);
    }

    @Override
    public Single<List<Rating>> fetchRatings(final String imdbId) {
        return movieClient.getMovieRating(imdbId);
    }

    @Override
    public Single<PersonMovieCredits> fetchPersonMovieCredits(final int personId) {
        return movieClient.getPersonMovieCredits(personId);
    }

    @Override
    public Single<ActorDetails> fetchActorDetails(final int actorId) {
        return Single.zip(fetchPersonMovieCredits(actorId), movieClient.getPersonDetails(actorId),
                          (personMovieCredits, personDetails) -> new ActorDetails(personMovieCredits,
                                                                                  personDetails.getName(),
                                                                                  personDetails.getBirthday(),
                                                                                  personDetails.getDeathday(),
                                                                                  personDetails.getProfession(),
                                                                                  personDetails.getProfileImageUrl(),
                                                                                  personDetails.getBiography(),
                                                                                  personDetails.getPlaceOfBirth()));
    }

    @Override
    public Flowable<List<Movie>> fetchFavoriteMoviesRecommendations() {
        return Flowable.combineLatest(movieCrudder.getFavoriteMoviesRecommendations(), movieCrudder.getAllFlowableFavoriteMoviesIds(),
                                      (movies, favorites) -> {
                                          final List<Movie> moviesWithFavorites = new ArrayList<>();
                                          for (final Movie movie : movies) {
                                              if (favorites.contains(movie.id)) {
                                                  moviesWithFavorites.add(movie.withIsFavorite(true));
                                              } else {
                                                  moviesWithFavorites.add(movie);
                                              }
                                          }
                                          return moviesWithFavorites;
                                      });
    }

    @Override
    public Flowable<List<Movie>> fetchWeatherMovieRecommendations() {
        return Flowable.combineLatest(movieCrudder.getWeatherMovieRecommendations(), movieCrudder.getAllFlowableFavoriteMoviesIds(),
                                      (movies, favorites) -> {
                                          final List<Movie> moviesWithFavorites = new ArrayList<>();
                                          for (final Movie movie : movies) {
                                              if (favorites.contains(movie.id)) {
                                                  moviesWithFavorites.add(movie.withIsFavorite(true));
                                              } else {
                                                  moviesWithFavorites.add(movie);
                                              }
                                          }
                                          return moviesWithFavorites;
                                      });
    }

    @Override
    public Single<List<Movie>> fetchMovies(final int page) {
        final List<Movie> moviesWithFavorites = new ArrayList<>();
        return Single.zip(movieClient.getMovies(page), movieCrudder.getAllFavoriteMoviesIds(),
                          (movies, favorites) -> {
                              for (final Movie movie : movies) {
                                  if (favorites.contains(movie.id)) {
                                      moviesWithFavorites.add(movie.withIsFavorite(true));
                                  } else {
                                      moviesWithFavorites.add(movie);
                                  }
                              }
                              return moviesWithFavorites;
                          });
    }

    @Override
    public Flowable<List<Movie>> queryMovies(final String title) {
        return Flowable.combineLatest(movieClient.getMovies(title).toFlowable(), movieCrudder.getAllFlowableFavoriteMoviesIds(),
                                      (movies, favorites) -> {
                                          final List<Movie> moviesWithFavorites = new ArrayList<>();
                                          for (final Movie movie : movies) {
                                              if (favorites.contains(movie.id)) {
                                                  moviesWithFavorites.add(movie.withIsFavorite(true));
                                              } else {
                                                  moviesWithFavorites.add(movie);
                                              }
                                          }
                                          return moviesWithFavorites;
                                      });
    }

    @Override
    public Completable insertMovies(final List<Movie> movies) {
        return movieCrudder.insertMovies(movies);
    }

    @Override
    public Completable setFavorite(final int movieId) {
        return movieCrudder.setFavorite(movieId);
    }

    @Override
    public Completable removeFavorite(final Integer movieId) {
        return movieCrudder.removeFavorite(movieId);
    }

    @Override
    public Flowable<List<Movie>> fetchFavorites() {
        return movieCrudder.getAllFavoriteMovies();
    }

    @Override
    public Flowable<List<Movie>> fetchFlowableMovies(final int page) {
        return Flowable.combineLatest(movieClient.getFlowableMovies(page), movieCrudder.getAllFlowableFavoriteMoviesIds(),
                                      (movies, favorites) -> {
                                          final List<Movie> moviesWithFavorites = new ArrayList<>();
                                          for (final Movie movie : movies) {
                                              if (favorites.contains(movie.id)) {
                                                  moviesWithFavorites.add(movie.withIsFavorite(true));
                                              } else {
                                                  moviesWithFavorites.add(movie);
                                              }
                                          }
                                          insertMovies(moviesWithFavorites);
                                          return moviesWithFavorites;
                                      });
    }

    @Override
    public Completable insertFavorite(final Movie movie) {
        return movieCrudder.insertMovie(movie);
    }

    @Override
    public Completable setPersonalNote(final Movie movie) {
        return movieCrudder.setPersonalNote(movie);
    }
}
