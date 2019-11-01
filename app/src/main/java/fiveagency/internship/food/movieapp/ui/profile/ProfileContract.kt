package fiveagency.internship.food.movieapp.ui.profile

interface ProfileContract {

    interface View {

        fun render(email: String?)
    }

    interface Presenter {

        fun start()

        fun setView(view: View)

        fun logOut()
    }
}