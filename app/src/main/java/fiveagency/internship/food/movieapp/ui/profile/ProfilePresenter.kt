package fiveagency.internship.food.movieapp.ui.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fiveagency.internship.food.data.database.model.DbFavoriteMoviesList
import fiveagency.internship.food.movieapp.ui.base.BasePresenter

private const val MOVIE_COUNTER_COLLECTION_NAME = "numberOfMovies"
private const val MOVIE_COUNTER_DOCUMENT_NAME = "counter"
private const val MOVIE_COUNTER_FIELD_NAME = "current"
private const val FAVORITE_COLLECTION_NAME = "favorites"

class ProfilePresenter(private val firebaseAuth: FirebaseAuth) : BasePresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override fun start() {
        val db = FirebaseFirestore.getInstance()

        firebaseAuth.currentUser?.let { user ->
            db.collection(MOVIE_COUNTER_COLLECTION_NAME).document(MOVIE_COUNTER_DOCUMENT_NAME).get().addOnSuccessListener { document ->
                db.collection(FAVORITE_COLLECTION_NAME).document(user.uid).get().addOnSuccessListener {
                    val allFavorites = it.toObject(DbFavoriteMoviesList::class.java) ?: DbFavoriteMoviesList.EMPTY
                    view.render(
                        user.displayName ?: "",
                        user.email ?: "",
                        document[MOVIE_COUNTER_FIELD_NAME] as Long? ?: 0L,
                        allFavorites.dbMoviesList.size
                    )
                }
            }
        } ?: logOut()
    }

    override fun setView(view: ProfileContract.View) {
        this.view = view
    }

    override fun logOut() {
        firebaseAuth.signOut()
        router.showLogInScreen()
    }
}