package fiveagency.internship.food.movieapp.ui.utils;

import android.content.Context;

public final class StringUtilImpl implements StringUtil {

    private final Context context;

    public StringUtilImpl(final Context context) {
        this.context = context;
    }

    @Override
    public String getStringResource(final int stringId) {
        return context.getResources().getString(stringId);
    }
}
