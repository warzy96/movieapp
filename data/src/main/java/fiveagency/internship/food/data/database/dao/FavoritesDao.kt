package fiveagency.internship.food.data.database.dao

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.aaronoe.rxfirestore.getFlowable
import fiveagency.internship.food.data.database.mappers.MovieModelMapper
import fiveagency.internship.food.data.database.model.DbFavoriteMovies
import fiveagency.internship.food.data.database.model.DbFavoriteMoviesList
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import org.reactivestreams.Publisher

private const val FAVORITE_COLLECTION_NAME = "favorites"

class FavoritesDao(
    private val movieModelMapper: MovieModelMapper,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    fun insertFavorites(dbFavoriteMovies: Set<DbFavoriteMovies>) {
        getUserUid()?.let { userId ->
            firebaseFirestore.collection(FAVORITE_COLLECTION_NAME)
                .document(userId)
                .get()
                .addOnSuccessListener {
                    val allFavorites = it.toObject(DbFavoriteMoviesList::class.java) ?: DbFavoriteMoviesList.EMPTY
                    firebaseFirestore.collection(FAVORITE_COLLECTION_NAME)
                        .document(userId)
                        .set(DbFavoriteMoviesList(allFavorites.dbMoviesList.toMutableSet().apply { addAll(dbFavoriteMovies) }.toList()))
                }
        }
    }

    fun deleteFavorite(dbFavoriteMovies: DbFavoriteMovies) {
        getUserUid()?.let { userId ->
            firebaseFirestore.collection(FAVORITE_COLLECTION_NAME)
                .document(userId)
                .get()
                .addOnSuccessListener {
                    val allFavorites = it.toObject(DbFavoriteMoviesList::class.java) ?: DbFavoriteMoviesList.EMPTY
                    firebaseFirestore.collection(FAVORITE_COLLECTION_NAME)
                        .document(userId)
                        .set(DbFavoriteMoviesList(allFavorites.dbMoviesList.toMutableSet().apply { remove(dbFavoriteMovies) }.toList()))
                }
        }
    }

    private fun getUserUid(): String? {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            return firebaseUser.uid
        } else {
            firebaseAuth.signOut()
        }
        return null
    }

    fun getAllMovieFavoritesId(): Single<List<Int>> {
        return Single.create { singleEmitter ->
            getUserUid()?.let { userId ->
                firebaseFirestore.collection(FAVORITE_COLLECTION_NAME)
                    .document(userId)
                    .get()
                    .addOnSuccessListener {
                        val allFavorites = it.toObject(DbFavoriteMoviesList::class.java) ?: DbFavoriteMoviesList.EMPTY
                        singleEmitter.onSuccess(allFavorites.dbMoviesList.map { it.id })
                    }
                    .addOnFailureListener {
                        singleEmitter.onError(it)
                    }
            }
        }
    }

    fun getAllFlowableFavoritesIds(): Flowable<List<Int>> {
        return getUserUid()?.let { userId ->
            firebaseFirestore.collection(FAVORITE_COLLECTION_NAME)
                .document(userId)
                .getFlowable<DbFavoriteMoviesList>(BackpressureStrategy.LATEST)
                .flatMap { moviesList ->
                    Publisher<List<Int>> {
                        val list = mutableListOf<Int>()
                        moviesList.dbMoviesList.forEach {
                            list.add(it.id)
                        }
                        it.onNext(list)
                    }
                }
        } ?: Flowable.just(listOf())
    }
}
