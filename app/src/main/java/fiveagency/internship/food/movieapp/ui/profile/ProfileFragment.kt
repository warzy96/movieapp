package fiveagency.internship.food.movieapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fiveagency.internship.food.movieapp.R
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent
import fiveagency.internship.food.movieapp.ui.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileContract.Presenter>(), ProfileContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setView(this)
    }

    override fun render() {
        presenter.start()
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}