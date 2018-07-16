package fiveagency.internship.food.domain.interactor.type;

public interface CompletableUseCase<Param> {

    void execute(Param param);
}
