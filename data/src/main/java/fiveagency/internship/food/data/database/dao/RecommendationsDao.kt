package fiveagency.internship.food.data.database.dao

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.aaronoe.rxfirestore.getFlowable
import fiveagency.internship.food.data.database.model.DbMovie
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

private const val RECOMMENDED_COLLECTION_NAME = "recommended"
private const val RECOMMENDED_BY_WEATHER_COLLECTION_NAME = "recommendedByWeather"
private const val RECOMMENDED_BY_FAVORITES_COLLECTION_NAME = "recommendedByFavorites"

class RecommendationsDao(private val firebaseFirestore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) {

    fun getFavoriteMoviesRecommendations(): Flowable<List<DbMovie>> {
        return getUserUid()?.let {
            firebaseFirestore.collection(RECOMMENDED_COLLECTION_NAME)
                .document(it)
                .collection(RECOMMENDED_BY_FAVORITES_COLLECTION_NAME)
                .getFlowable<DbMovie>(BackpressureStrategy.LATEST)
        } ?: Flowable.just(listOf())
    }

    fun getWeatherMovieRecommendations(): Flowable<List<DbMovie>> {
        return getUserUid()?.let {
            firebaseFirestore.collection(RECOMMENDED_COLLECTION_NAME)
                .document(it)
                .collection(RECOMMENDED_BY_WEATHER_COLLECTION_NAME)
                .getFlowable<DbMovie>(BackpressureStrategy.LATEST)
        } ?: Flowable.just(listOf())
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
}