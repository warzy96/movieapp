package fiveagency.internship.food.domain.interactor.type;

import io.reactivex.Completable;

public interface CompletableUseCase<Param> {

    Completable execute(Param param);
}
