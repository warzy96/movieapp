package fiveagency.internship.food.movieapp.ui.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fiveagency.internship.food.movieapp.R;

public final class DummyFragment2 extends Fragment {

    public static DummyFragment2 newInstance() {
        DummyFragment2 dummyFragment2 = new DummyFragment2();
        return dummyFragment2;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dummy_fragment_2, container, false);
    }
}
