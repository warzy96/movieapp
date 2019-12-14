package fiveagency.internship.food.movieapp.ui.actordetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fiveagency.internship.food.movieapp.R
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent
import fiveagency.internship.food.movieapp.ui.base.BaseFragment

class ActorDetailsDialogFragment : BaseFragment<ActorDetailsContract.Presenter>(), ActorDetailsContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_actor_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        presenter.onStart()
    }

    override fun render() {
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }

}