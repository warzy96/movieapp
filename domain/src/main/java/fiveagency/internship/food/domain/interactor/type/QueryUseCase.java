package fiveagency.internship.food.domain.interactor.type;

public interface QueryUseCase<Param, Result> {

    Result execute(Param param);
}
