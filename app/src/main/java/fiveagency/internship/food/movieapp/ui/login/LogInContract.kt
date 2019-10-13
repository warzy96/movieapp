package fiveagency.internship.food.movieapp.ui.login

import android.content.Intent
import com.facebook.login.widget.LoginButton

interface LogInContract {

    interface Presenter {

        fun start()

        fun setView(view: LogInContract.View)

        fun onFacebookLogInButtonClicked(facebookLogInButton: LoginButton)

        fun onStop()

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

        fun onGoogleLogInButtonClicked()
    }

    interface View {

        fun render()
    }
}