package fiveagency.internship.food.movieapp.ui.actordetails

import fiveagency.internship.food.domain.model.ActorDetails

interface ActorDetailsContract {

    interface View {
        fun render(actorDetails: ActorDetails)
    }

    interface Presenter {
        fun onStart(actorId: Int)

        fun setView(view: View)
    }
}