package fiveagency.internship.food.movieapp.ui.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fiveagency.internship.food.movieapp.R;

public final class DummyFragment1 extends android.support.v4.app.Fragment {

    public static DummyFragment1 newInstance() {
        DummyFragment1 dummyFragment1 = new DummyFragment1();
        return dummyFragment1;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dummy_fragment_1, container, false);
    }
}
