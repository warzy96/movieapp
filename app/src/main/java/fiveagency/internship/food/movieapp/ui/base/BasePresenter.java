package fiveagency.internship.food.movieapp.ui.base;

public abstract class BasePresenter<View> {

    public final View view;

    public BasePresenter(final View view) {
        this.view = view;
    }
}
