package fiveagency.internship.food.data.network.client;

public interface ResponseListener<Result> {

    void onResult(Result result);

    void onFailure(Throwable t);
}
