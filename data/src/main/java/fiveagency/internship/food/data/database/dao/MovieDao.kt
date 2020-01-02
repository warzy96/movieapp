package fiveagency.internship.food.data.database.dao

import com.google.firebase.firestore.FirebaseFirestore
import de.aaronoe.rxfirestore.getFlowable
import fiveagency.internship.food.data.database.mappers.MovieModelMapper
import fiveagency.internship.food.data.database.model.DbMovie
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single

private const val MOVIE_COLLECTION_NAME = "movies"

class MovieDao(private val firebaseFirestore: FirebaseFirestore, private val movieModelMapper: MovieModelMapper) {

    fun insertAllMovies(movies: List<DbMovie>) {
        movies.forEach {
            firebaseFirestore.collection(MOVIE_COLLECTION_NAME)
                .document(it.id.toString())
                .set(it)
        }

    }

    fun deleteMovie(dbMovie: DbMovie) {
        firebaseFirestore.collection(MOVIE_COLLECTION_NAME)
            .document(dbMovie.id.toString())
            .delete()
    }

    fun setPersonalNote(personalNote: String, movieId: Int) {
        firebaseFirestore
            .collection(MOVIE_COLLECTION_NAME)
            .document(movieId.toString())
            .get()
            .addOnSuccessListener {
                val dbMovie: DbMovie = it.toObject(DbMovie::class.java) ?: DbMovie.EMPTY
                dbMovie.personalNote = personalNote
                insertMovie(dbMovie)
            }
    }

    private fun insertMovie(dbMovie: DbMovie) {
        firebaseFirestore
            .collection(MOVIE_COLLECTION_NAME)
            .document(dbMovie.id.toString())
            .set(dbMovie)
    }

    fun getMovie(movieId: Int): Single<DbMovie> {
        return Single.create { singleEmitter ->
            firebaseFirestore
                .collection(MOVIE_COLLECTION_NAME)
                .document(movieId.toString())
                .get()
                .addOnSuccessListener {
                    val dbMovie: DbMovie = it.toObject(DbMovie::class.java) ?: DbMovie.EMPTY
                    singleEmitter.onSuccess(dbMovie)
                }
                .addOnFailureListener {
                    singleEmitter.onError(it)
                }
        }
    }

    fun getAllMovies(): Flowable<List<DbMovie>> {
        return firebaseFirestore.collection(MOVIE_COLLECTION_NAME).getFlowable(BackpressureStrategy.LATEST)
    }
}