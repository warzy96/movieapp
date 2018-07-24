package fiveagency.internship.food.movieapp.ui.movieslist.diffutil;

public abstract class DiffUtilViewModel<T> {

    public final T id;

    public DiffUtilViewModel(final T id) {
        this.id = id;
    }
}
