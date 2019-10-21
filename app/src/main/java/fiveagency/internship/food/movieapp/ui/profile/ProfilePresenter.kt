package fiveagency.internship.food.movieapp.ui.profile

import fiveagency.internship.food.movieapp.ui.base.BasePresenter

class ProfilePresenter : BasePresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override fun start() {
        view.render()
    }

    override fun setView(view: ProfileContract.View) {
        this.view = view
    }
}