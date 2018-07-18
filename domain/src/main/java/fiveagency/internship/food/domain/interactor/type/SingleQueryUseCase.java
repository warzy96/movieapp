package fiveagency.internship.food.domain.interactor.type;

import io.reactivex.Single;

public interface SingleQueryUseCase<Param, Result> {

    Single<Result> execute(Param param);
}
