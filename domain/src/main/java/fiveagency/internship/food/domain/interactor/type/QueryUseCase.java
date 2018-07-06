package fiveagency.internship.food.domain.interactor.type;

public interface QueryUseCase<Param, Result> {

    interface Callback<Result> {

        void onSuccess(Result result);

        void onFailure(Throwable throwable);
    }

    void execute(Param param, Callback<Result> callback);
}
