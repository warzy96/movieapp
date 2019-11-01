package fiveagency.internship.food.movieapp.ui.profile

import com.google.firebase.auth.FirebaseAuth
import fiveagency.internship.food.movieapp.ui.base.BasePresenter

class ProfilePresenter(private val firebaseAuth: FirebaseAuth) : BasePresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override fun start() {
        firebaseAuth.currentUser?.let {
            view.render(it.email)
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