package fiveagency.internship.food.movieapp.ui.profile

interface ProfileContract {

    interface View {

        fun render(displayName: String, email: String, movieCount: Long, favoritesCount: Int)
    }

    interface Presenter {

        fun start()

        fun setView(view: View)

        fun logOut()
    }
}