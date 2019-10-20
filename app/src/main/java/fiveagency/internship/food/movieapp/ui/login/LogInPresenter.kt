package fiveagency.internship.food.movieapp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import fiveagency.internship.food.movieapp.ui.base.BasePresenter

const val RC_SIGN_IN = 22

class LogInPresenter(private val firebaseAuthUIIntent: Intent) : BasePresenter<LogInContract.View>(),
    LogInContract.Presenter {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun start() {
        firebaseAuth = FirebaseAuth.getInstance()

        /**
         * If user already exists, start movies list screen
         */
        if (isUserSignedIn()) {
            router.showActivityFragmentScreen()
        } else {
            showFirebaseLogInScreen()
        }

    }

    private fun showFirebaseLogInScreen() {
        (view as Fragment).startActivityForResult(firebaseAuthUIIntent, RC_SIGN_IN)
    }

    private fun isUserSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun setView(view: LogInContract.View) {
        this.view = view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                router.showActivityFragmentScreen()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                if (response != null) showFirebaseLogInScreen()
            }
        }
    }
}