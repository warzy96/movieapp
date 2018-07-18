package fiveagency.internship.food.movieapp.ui.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fiveagency.internship.food.movieapp.R;

public final class ActivityFragment extends Fragment {

    public static final String TAG = "ActivityFragment";
    private ViewPager viewPager;

    MoviePagerAdapter moviePagerAdapter;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviePagerAdapter = new MoviePagerAdapter(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_layout, container, false);
    }

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(moviePagerAdapter);
    }
}
