package fiveagency.internship.food.movieapp.ui.login

import android.content.Intent
import android.util.Log
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import fiveagency.internship.food.movieapp.ui.base.BasePresenter

const val RC_SIGN_IN = 22

class LogInPresenter(private val callbackManager: CallbackManager) : BasePresenter<LogInContract.View>(), LogInContract.Presenter {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun start() {
        firebaseAuth = FirebaseAuth.getInstance()

        /**
         * If user already exists, start movies list screen
         */
        if (isUserSignedIn()) {
            router.showMoviesListScreen()
        }
    }

    private fun isUserSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun setView(view: LogInContract.View) {
        this.view = view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RC_SIGN_IN -> handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data))
            else -> callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            //TODO: Send email, id and name to FireBase
            //TODO: Start movies list screen
            Log.d("login", account?.email)
        } catch (e: ApiException) {
            Log.e("login", e.statusCode.toString())
            e.stackTrace.forEach {
                Log.e("login", it.toString())
            }
        }

    }
}