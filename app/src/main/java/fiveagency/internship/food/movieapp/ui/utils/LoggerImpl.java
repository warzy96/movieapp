package fiveagency.internship.food.movieapp.ui.utils;

public final class LoggerImpl implements Logger {

    public void log(final Throwable throwable) {
        throwable.printStackTrace();
    }
}
