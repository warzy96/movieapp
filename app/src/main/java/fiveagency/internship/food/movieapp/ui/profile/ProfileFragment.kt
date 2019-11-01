package fiveagency.internship.food.movieapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fiveagency.internship.food.movieapp.R
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent
import fiveagency.internship.food.movieapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<ProfileContract.Presenter>(), ProfileContract.View {

    companion object {
        const val TAG = "ProfileFragment"

        @JvmStatic
        fun newInstance(): Fragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun render(email: String?) {
        userEmailTextView.text = email
        signOutTextView.setOnClickListener { presenter.logOut() }
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}