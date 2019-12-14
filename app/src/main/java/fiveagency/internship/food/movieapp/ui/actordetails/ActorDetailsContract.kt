package fiveagency.internship.food.movieapp.ui.actordetails

interface ActorDetailsContract {

    interface View {
        fun render()
    }

    interface Presenter {
        fun onStart()
    }
}