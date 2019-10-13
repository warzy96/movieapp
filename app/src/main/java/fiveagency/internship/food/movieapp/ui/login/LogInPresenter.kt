package fiveagency.internship.food.movieapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import fiveagency.internship.food.movieapp.ui.base.BasePresenter

const val RC_SIGN_IN = 22

class LogInPresenter(private val callbackManager: CallbackManager) : BasePresenter<LogInContract.View>(), LogInContract.Presenter {

    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun start() {
        firebaseAuth = FirebaseAuth.getInstance()
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient((view as? Fragment)?.context!!, googleSignInOptions)

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

    override fun onFacebookLogInButtonClicked(facebookLogInButton: LoginButton) {
        facebookLogInButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                GraphRequest.newMeRequest(loginResult.accessToken) { result, response ->
                    //TODO: Send email, id and name to FireBase
                    //TODO: Start movies list screen
                    Log.d("login", result.getString("email"))
                }.apply {
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,email")
                    this.parameters = parameters
                }.executeAsync()
            }

            override fun onCancel() {
                LoginManager.getInstance().logOut()
            }

            override fun onError(exception: FacebookException) {
                loggerImpl.log(exception)
            }
        })
    }

    override fun onGoogleLogInButtonClicked() {
        val signInIntent = googleSignInClient.signInIntent
        (view as Fragment).activity?.startActivityForResult(signInIntent, RC_SIGN_IN)
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