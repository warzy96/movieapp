package fiveagency.internship.food.domain.repository.listeners;

public interface RepositoryListener<Result> {

    void onResult(Result result);

    void onFailure(Throwable t);
}
