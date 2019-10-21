package fiveagency.internship.food.movieapp.ui.profile

interface ProfileContract {

    interface View {

        fun render()
    }

    interface Presenter {

        fun start()

        fun setView(view: View)
    }
}