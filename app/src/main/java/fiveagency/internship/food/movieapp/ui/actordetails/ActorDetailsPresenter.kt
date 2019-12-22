package fiveagency.internship.food.movieapp.ui.actordetails

import fiveagency.internship.food.domain.interactor.type.GetActorDetailsUseCase
import fiveagency.internship.food.movieapp.ui.base.BasePresenter
import javax.inject.Inject

class ActorDetailsPresenter : BasePresenter<ActorDetailsContract.View>(), ActorDetailsContract.Presenter {

    @Inject
    lateinit var getActorDetailsUseCase: GetActorDetailsUseCase

    override fun onStart(actorId: Int) {
        compositeDisposable.add(
            getActorDetailsUseCase.execute(actorId)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({ view.render(it) },
                    { loggerImpl.log(it) })
        )
    }

    override fun setView(view: ActorDetailsContract.View) {
        this.view = view
    }
}