package fiveagency.internship.food.movieapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent
import fiveagency.internship.food.movieapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LogInFragment : LogInContract.View, BaseFragment<LogInContract.Presenter>() {

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return LogInFragment()
        }

        const val TAG = "LogInFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(fiveagency.internship.food.movieapp.R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)

        facebookLogInButton.fragment = this
        facebookLogInButton.setOnClickListener { presenter.onFacebookLogInButtonClicked(facebookLogInButton) }

        googleSignInButton.setOnClickListener { presenter.onGoogleLogInButtonClicked() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun render() {

    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}