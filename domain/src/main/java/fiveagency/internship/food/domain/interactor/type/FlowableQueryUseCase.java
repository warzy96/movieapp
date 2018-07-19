package fiveagency.internship.food.domain.interactor.type;

import io.reactivex.Flowable;

public interface FlowableQueryUseCase<Param, Result> {

    Flowable<Result> execute(Param param);
}
