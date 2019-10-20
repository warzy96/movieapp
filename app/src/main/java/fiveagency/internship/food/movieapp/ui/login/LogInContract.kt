package fiveagency.internship.food.movieapp.ui.login

import android.content.Intent

interface LogInContract {

    interface Presenter {

        fun start()

        fun setView(view: LogInContract.View)

        fun onStop()

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    interface View {

        fun render()
    }
}