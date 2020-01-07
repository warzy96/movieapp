package fiveagency.internship.food.movieapp.ui.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fiveagency.internship.food.movieapp.ui.base.BasePresenter

private const val MOVIE_COLLECTION_NAME = "movies"


class ProfilePresenter(private val firebaseAuth: FirebaseAuth) : BasePresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override fun start() {
        var count = 0
        val  db = FirebaseFirestore.getInstance()

        firebaseAuth.currentUser?.let {
            db.collection(MOVIE_COLLECTION_NAME).get().addOnSuccessListener { documents ->
                count = documents.size()
                view.render(it.displayName,it.email, count)
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